package com.test_jsoup.app.LEASTSQUARE;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class Mape {
    String  TAG ="MapeTAG";
    private Context context;
    private ArrayList<TabelMape> tabelMapes =new ArrayList<>();
    private double globalValue=0;
    public Mape(Context context) {
        this.context = context;
        addValueTable();
    }

    public void startCalculate(){
        for (int i = 0; i <tabelMapes.size() ; i++) {
            TabelMape tabelMape = tabelMapes.get(i);
            double valueAiFi = tabelMape.getAi() - tabelMape.getFi();
            double valueAiFiAbsolue = Math.abs(valueAiFi);
            double valueAiFiAbsolutePer2= valueAiFiAbsolue/tabelMape.getAi();

            tabelMape.setAiFi(valueAiFi);
            tabelMape.setAiFiAbsolue(valueAiFiAbsolue);
            tabelMape.setAiFiAbsolutePer2(valueAiFiAbsolutePer2);
            tabelMapes.set(i, tabelMape);
            globalValue = globalValue + tabelMapes.get(i).getAiFiAbsolutePer2();
        }
        for (TabelMape tm : tabelMapes){
//            Log.d(TAG, "startCalculate: "+tm.getIndexWaktu() + " "+tm.getAi() + " "+tm.getFi()
//                    + " "+tm.getAiFi() + " " +Math.round( tm.getAiFiAbsolue()) + " "+tm.getAiFiAbsolutePer2());

            Log.d(TAG, "startCalculate: "  +tm.getAiFiAbsolue() + " "+tm.getAiFiAbsolutePer2());
        }

        double hasil = (globalValue/tabelMapes.size())*100;
        Log.d(TAG, "startCalculate: "+hasil);
    }

    private void addValueTable() {
        tabelMapes.add(new TabelMape("1", 10,10.5,0,0,0));
        tabelMapes.add(new TabelMape("2", 5,10.38,0,0,0));
        tabelMapes.add(new TabelMape("3", 8,10.27,0,0,0));
        tabelMapes.add(new TabelMape("4", 20,10.15,0,0,0));
        tabelMapes.add(new TabelMape("5", 15,10.03,0,0,0));
        tabelMapes.add(new TabelMape("6", 13,9.92,0,0,0));
        tabelMapes.add(new TabelMape("7", 12,9.8,0,0,0));
        tabelMapes.add(new TabelMape("8", 18,9.69,0,0,0));
        tabelMapes.add(new TabelMape("9", 10,9.57,0,0,0));
        tabelMapes.add(new TabelMape("10", 8,9.46,0,0,0));
        tabelMapes.add(new TabelMape("11", 6,9.34,0,0,0));
        tabelMapes.add(new TabelMape("12", 10,9.23,0,0,0));
    }




}
