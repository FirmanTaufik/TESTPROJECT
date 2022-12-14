package com.test_jsoup.app.SupperChat.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jivesoftware.smackx.mam.MamManager;
import org.jxmpp.jid.EntityJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.android.material.snackbar.Snackbar;
import com.test_jsoup.app.R;
import com.test_jsoup.app.SupperChat.Adapter.ChatAdapter;
import com.test_jsoup.app.SupperChat.Model.Chat;
import com.test_jsoup.app.SupperChat.Model.ChatConnection;
import com.test_jsoup.app.SupperChat.Util.ConnectionDetector;

import javax.net.ssl.X509TrustManager;

public class ChatActivity extends AppCompatActivity {

    private XMPPTCPConnectionConfiguration mConfiguration;
    private AbstractXMPPConnection mConnection;
    private ChatManager mChatmanager;
    private org.jivesoftware.smack.chat.Chat mChat;
    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Chat mChatFunctionModel;
    private EditText et_msgToBeSent;    
    private ImageView imv_sendButton;
    private ProgressBar pb_progressbar;
    private LinearLayout ll_chat_window;
    private Button btn_retry;
    private ChatConnection mChatConnection;
    private EntityJid friendJID;

    private List<Chat> chatsList = new ArrayList<Chat>();
    private String username, password, port, serviceName, host, friendID;
    private String messageToSend;
    private String chatWindowTitle;

    /**
     * Constant values
     */
    private final int SnackbarShowingTime = 2500;
    private final String unableToConnectMsg = "Unable to establish chat connection!";
    private final String noConnectionMsg = "Chat not connected. Try again after sometime!";
    private final String connectedMsg = "Chat connected!";


    public static void startChat(ChatConnection chatConnection, Context context){
        Intent intent = new Intent(context, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chatConnectionData", chatConnection);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);
        getIntentData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        initViews();


        // If internet is available, connect to chat server, else show 'cannot connect' message
        ConnectionDetector detector = new ConnectionDetector(getApplicationContext());
        if(detector.isNetworkAvailable()){
            connectToServer();
        }
        else{
            cannotConnectMessage();
        }

        registerListeners();
    }




    public void getIntentData(){
        
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        mChatConnection = (ChatConnection)bundle.getSerializable("chatConnectionData");

        chatWindowTitle = mChatConnection.getchatWindowTitle();

        /**
         * Credentials for the customer (who is using the App)
         */
        username = mChatConnection.getxmppCustomerUsername();
        password =  mChatConnection.getxmppCustomerPassword();
        port =  mChatConnection.getxmppPortNo();
        serviceName =  mChatConnection.getxmppServiceName();
        host = mChatConnection.getxmppHostName();

        /**
         * Credentials of customer support executive (who is sitting at the other end on a XMPP chat client for support)
         */
        friendID = mChatConnection.getxmppIdOfSupportExecutive();        
    }




    public void initViews(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(chatWindowTitle);

        ll_chat_window = (LinearLayout)findViewById(R.id.chat_window);
        pb_progressbar = (ProgressBar)findViewById(R.id.progress_bar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        et_msgToBeSent = (EditText) findViewById(R.id.msgToBeSent);
        et_msgToBeSent.setFocusable(true);
        imv_sendButton = (ImageView) findViewById(R.id.sendButton);
        btn_retry = (Button)findViewById(R.id.retry);
        
        // For the RecyclerView and Adapter
        messageToSend = null;
        mChatFunctionModel = new Chat();
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ChatAdapter(chatsList);
        mRecyclerView.setAdapter(mAdapter);
    }




    public void registerListeners(){

        // the retry button click functionality goes here
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pb_progressbar.setVisibility(View.VISIBLE);
                btn_retry.setVisibility(View.GONE);

                ConnectionDetector detector = new ConnectionDetector(getApplicationContext());
                if(detector.isNetworkAvailable()){
                    connectToServer();
                }
                else{
                    cannotConnectMessage();
                }
            }
        });

        // sending the chat message
        imv_sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(mConnection.isConnected()){
                    messageToSend = et_msgToBeSent.getText().toString();
                    try {
                        if(!messageToSend.trim().equals("")){

                            et_msgToBeSent.setText("");

                            mChatFunctionModel = new Chat();
                            mChatFunctionModel.setSentMessage(messageToSend);
                            mChatFunctionModel.setSentTime(new Date().getTime());
                            mChatFunctionModel.setGotMessage("");
                            chatsList.add(mChatFunctionModel);
                            mChat.sendMessage(messageToSend);
                            mAdapter.notifyDataSetChanged();
                            mRecyclerView.smoothScrollToPosition(chatsList.size());
                            et_msgToBeSent.setFocusable(true);
                        }

                    } catch (SmackException.NotConnectedException notConnectedException) {
                        notConnectedException.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    showSnackBar(unableToConnectMsg);
                }
            }

        });
    }

    


    public void connectToServer(){
        btn_retry.setVisibility(View.GONE);
        pb_progressbar.setVisibility(View.VISIBLE);
        new ConnectToXMPPserver().execute();

    }



    public class ConnectToXMPPserver extends AsyncTask<Object, Object, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pb_progressbar.setVisibility(View.VISIBLE);
            ll_chat_window.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(Object... params) {

            try {

            // Create a connection to the XMPP chat server on a specific port.
            mConfiguration = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword(username, password)
                    .setHost(host)
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.ifpossible)
                    .setXmppDomain(JidCreate.domainBareFrom(serviceName))
                   // .setPort(Integer.parseInt(port))
                    .setKeystoreType(null)

                    .setDebuggerEnabled(true)
                    .setResource("stork")
                    .setCustomX509TrustManager(new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    })
                    .build();


                mConnection = new XMPPTCPConnection(mConfiguration);
                mConnection.connect();
                mConnection.login();

            } catch (SmackException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XMPPException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            if(mConnection.isConnected()){
                return "connected";
            }
            else{
                return "notConnected";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result.equals("connected")){
                try {
                    ConnectedMessage();

                    pb_progressbar.setVisibility(View.GONE);
                    ll_chat_window.setVisibility(View.VISIBLE);

                    friendJID = (EntityJid) JidCreate.from(friendID);
                    MamManager mamManager=MamManager.getInstanceFor(mConnection);
                    MamManager.MamQueryResult mamQueryResult = mamManager.queryArchive(friendJID);
                    List<Forwarded> forwardedMessages=mamQueryResult.forwardedMessages;
                    Iterator<Forwarded> forwardedIterator=forwardedMessages.iterator();
                    while (forwardedIterator.hasNext()){
                        Forwarded forwarded=forwardedIterator.next();
                        Stanza stanza=forwarded.getForwardedStanza();
                        if (stanza instanceof Message) {
                            String messageId=stanza.getStanzaId();
                           // xmppTcpConnection.processMessage((Message) stanza);
                            Log.i( "onPostExecute: ",messageId);
                        }
                    }
                    mChatmanager = mChatmanager.getInstanceFor(mConnection);
                    mChat = mChatmanager.createChat(friendJID, new ChatMessageListener() {
                        public void processMessage(org.jivesoftware.smack.chat.Chat chat, Message message) {

                    // receiving the chat message from the support executive
                            if(!message.getBody().trim().equals("")){
                                mChatFunctionModel = new Chat();
                                mChatFunctionModel.setGotMessage(message.getBody());
                                mChatFunctionModel.setGotTime(new Date().getTime());
                                mChatFunctionModel.setSentMessage("");
                                chatsList.add(mChatFunctionModel);

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        mAdapter.notifyDataSetChanged();
                                        mRecyclerView.smoothScrollToPosition(chatsList.size());
                                        et_msgToBeSent.setFocusable(true);
                                    }
                                });

                            }
                        }
                    });


                } catch (XmppStringprepException e) {
                    e.printStackTrace();
                } catch (XMPPException.XMPPErrorException e) {
                    e.printStackTrace();
                } catch (SmackException.NotConnectedException e) {
                    e.printStackTrace();
                } catch (SmackException.NoResponseException e) {
                    e.printStackTrace();
                } catch (SmackException.NotLoggedInException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                cannotConnectMessage();
            }

        }

    }



    public void cannotConnectMessage(){
        showSnackBar(noConnectionMsg);
        pb_progressbar.setVisibility(View.GONE);
        btn_retry.setVisibility(View.VISIBLE);
    }



    public void ConnectedMessage(){
        showSnackBar(connectedMsg);
        pb_progressbar.setVisibility(View.GONE);
        btn_retry.setVisibility(View.GONE);
    }



    @Override
    protected void onResume() {
       super.onResume();
       if(mConnection != null){
           if(mConnection.isConnected() == false){
               ConnectionDetector detector = new ConnectionDetector(getApplicationContext());
               if(detector.isNetworkAvailable()){
                   connectToServer();
               }
               else{
                   cannotConnectMessage();
               }
           }
       }
    }



    @Override
    protected void onPause() {
        super.onPause();
       /* if(mConnection != null){
            mConnection.disconnect();
        }*/
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mConnection != null){
            mConnection.disconnect();
        }
    }



    public void showSnackBar(String message){
        Snackbar snackbar = Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_LONG);
        snackbar.setDuration(SnackbarShowingTime)
                .show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



 }

