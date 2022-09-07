package com.test_jsoup.app.LEASTSQUARE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.test_jsoup.app.R;

import java.util.ArrayList;

public class LEASTSQUARE extends AppCompatActivity {
    private String TAG ="LEASTSQUARETAG";
    private ArrayList<TabelModel> tabelModels;
    private ArrayList<TabelRamal> tabelRamals;
    private int totalY=0;
    private int totalXY=0;
    private int totalXkuadrat=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leastsquare);
        tabelModels = new ArrayList<>();
        tabelRamals = new ArrayList<>();
        setDataModel();
        setDataTabelRamal();
        if(isGanjil(tabelModels.size())) {
            int positionMid =  calGanjilGetMid();
            setTableX(positionMid, true);
        }else {
            setTableX(0, false);
        }

        setTableXY();

        setTableXKuadrat();

        for (int i = 0; i <tabelModels.size() ; i++) {
            TabelModel tabelModel = tabelModels.get(i);
            totalY = totalY+tabelModel.getY();
            totalXY = totalXY+tabelModel.getXy();
            totalXkuadrat = totalXkuadrat+tabelModel.getxKuadrat();
        }
        
        calPeramalan();

        //CalculateMape
       Mape mape = new Mape(this);
       mape.startCalculate();
    }



    private void calPeramalan() {
        for (TabelModel t : tabelModels){
            Log.d(TAG, "calPeramalanLoop: "+t.getY() +" "+t.getX() +" "+t.getxKuadrat() +" "+t.getXy());
        }

        //rumus a = y/n;
        double a = totalY/tabelModels.size();

        //rumus b = xy/xkuadrat;
        double b = totalXY/totalXkuadrat;


        //rumus tahun ramalan yTahunRamal = a+b (x tahun ramal)
        double xTahunRamal = tabelModels.get(tabelModels.size()-1).getX()+2;
        double nilaiA = a + (b*xTahunRamal);

          int  gy = 0, gx = 0, gxy= 0,gxKuadrat= 0;
        for (TabelModel tabelModel :tabelModels) {
            gy =  gy +tabelModel.getY();
            gx = gx + tabelModel.getX();
            gxy =gxy+ tabelModel.getXy();
            gxKuadrat = gxKuadrat +tabelModel.getxKuadrat();
        }

        double nilaiB = gxy / Double.valueOf(gxKuadrat);
        Log.d(TAG, "calPeramalan: "+gy + " "+gx +" "+gxKuadrat +" "+gxy);
        Log.d(TAG, "calPeramalan: "+ nilaiA+ " "+nilaiB  );

        //Hasil yang diramalkan
        //Y = a+bx

        for (TabelRamal tr : tabelRamals){
            double value = Math.round( (nilaiB)*tr.getX())+nilaiA ;
            Log.d(TAG, "calPeramalan: Hasil Ramalan " +value);
        }

    }

    private void setTableXKuadrat() {
        for (int i = 0; i <tabelModels.size() ; i++) {
            TabelModel tabelModel = tabelModels.get(i);
            int val =  tabelModel.getX() * tabelModel.getX();
            tabelModel.setxKuadrat(val);
            tabelModels.set(i,tabelModel);
        }
    }

    private void setTableXY() {
        for (int i = 0; i <tabelModels.size() ; i++) {
            TabelModel tabelModel = tabelModels.get(i);
            int val =  tabelModel.getY() * tabelModel.getX();
            tabelModel.setXy(val);
            tabelModels.set(i,tabelModel);
        }
    }

    private void setTableX(int positionMid, boolean isGanjil) {
        int pos =positionMid;
        if (isGanjil) {
            for (int i = 0; i <tabelModels.size() ; i++) {
                TabelModel tabelModel = tabelModels.get(i);
                int x =  i-pos;
                tabelModel.setX(x);
            }

        }else {
            for (int i = 0; i <tabelModels.size() ; i++) {
                TabelModel tabelModel = tabelModels.get(i);
                int u = (i+1) +(i-tabelModels.size());
                tabelModel.setX(u);
            }
        }
    }

    private int calGanjilGetMid() {
        int n=tabelModels.size();
        String a[]=new String[n];
        for (int i = 0; i <n ; i++) {
            a[i]=tabelModels.get(i).getBulan();
        }

        String m="";
        if(n%2==1)
        {
            m=a[(n+1)/2-1];
        }
        else
        {
            int u = Integer.valueOf(a[n/2-1]+a[n/2]);
            m=String.valueOf((u)/2);
        }
        Log.d(TAG, "calGanjilGetMid: "+m);
        for (int i = 0; i <n ; i++) {
            if (m.equals(tabelModels.get(i).getBulan())) {
                return i;
            }
        }
        return 0;
    }

    private boolean isGanjil(int size) {
        if (size %2==1){
            return true;
        }
        return false;
    }

    private void setDataModel() {
        tabelModels.add(new TabelModel("Jan-21",10,0,0,0));
        tabelModels.add(new TabelModel("Feb-21",5,0,0,0));
        tabelModels.add(new TabelModel("Mar-21",8,0,0,0));
        tabelModels.add(new TabelModel("Apr-21",20,0,0,0));
        tabelModels.add(new TabelModel("May-21",15,0,0,0));
        tabelModels.add(new TabelModel("Jun-21",13,0,0,0));
        tabelModels.add(new TabelModel("Jul-21",12,0,0,0));
        tabelModels.add(new TabelModel("Agust-21",18,0,0,0));
        tabelModels.add(new TabelModel("Sep-21",10,0,0,0));
        tabelModels.add(new TabelModel("Okt-21",8,0,0,0));
        tabelModels.add(new TabelModel("Nov-21",6,0,0,0));
        tabelModels.add(new TabelModel("Des-21",10,0,0,0));
    }
    private void setDataTabelRamal() {
        tabelRamals.add(new TabelRamal("Jan",13));
        tabelRamals.add(new TabelRamal("Feb",15));
        tabelRamals.add(new TabelRamal("Feb",17));
        tabelRamals.add(new TabelRamal("Apr",19));
        tabelRamals.add(new TabelRamal("Mei",21));
        tabelRamals.add(new TabelRamal("Jun",23));
        tabelRamals.add(new TabelRamal("Jul",25));
        tabelRamals.add(new TabelRamal("Agus",27));
        tabelRamals.add(new TabelRamal("Sep",29));
        tabelRamals.add(new TabelRamal("Okt",31));
        tabelRamals.add(new TabelRamal("Nov",33));
        tabelRamals.add(new TabelRamal("Des",35));
    }
}