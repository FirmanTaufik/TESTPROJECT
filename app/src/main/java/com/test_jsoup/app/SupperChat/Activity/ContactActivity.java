package com.test_jsoup.app.SupperChat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.test_jsoup.app.R;
import com.test_jsoup.app.SupperChat.Model.ChatConnection;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ChatConnection mChatConnection = new ChatConnection();
                mChatConnection.setxmppCustomerUsername("888");
                mChatConnection.setxmppCustomerPassword("888");
         //       mChatConnection.setxmppPortNo("5222");
                mChatConnection.setxmppHostName("jitsi.indahonline.com");
                mChatConnection.setxmppServiceName("jitsi.indahonline.com");
                mChatConnection.setxmppIdOfSupportExecutive("10000@jitsi.indahonline.com");
                mChatConnection.setchatWindowTitle("Support chat window");
                startChat(mChatConnection, ContactActivity.this);
            }
        },1500);
    }


    public static void startChat(ChatConnection chatConnection, Context context){
        Intent intent = new Intent(context, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("chatConnectionData", chatConnection);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}