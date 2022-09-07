package com.test_jsoup.app.LEASTSQUARE;

public class TabelMape {
    private String indexWaktu;
    private double Ai, Fi, AiFi, AiFiAbsolue, AiFiAbsolutePer2;

    public TabelMape(String indexWaktu, double ai, double fi, double aiFi, double aiFiAbsolue, double aiFiAbsolutePer2) {
        this.indexWaktu = indexWaktu;
        Ai = ai;
        Fi = fi;
        AiFi = aiFi;
        AiFiAbsolue = aiFiAbsolue;
        AiFiAbsolutePer2 = aiFiAbsolutePer2;
    }

    public String getIndexWaktu() {
        return indexWaktu;
    }

    public void setIndexWaktu(String indexWaktu) {
        this.indexWaktu = indexWaktu;
    }

    public double getAi() {
        return Ai;
    }

    public void setAi(double ai) {
        Ai = ai;
    }

    public double getFi() {
        return Fi;
    }

    public void setFi(double fi) {
        Fi = fi;
    }

    public double getAiFi() {
        return AiFi;
    }

    public void setAiFi(double aiFi) {
        AiFi = aiFi;
    }

    public double getAiFiAbsolue() {
        return AiFiAbsolue;
    }

    public void setAiFiAbsolue(double aiFiAbsolue) {
        AiFiAbsolue = aiFiAbsolue;
    }

    public double getAiFiAbsolutePer2() {
        return AiFiAbsolutePer2;
    }

    public void setAiFiAbsolutePer2(double aiFiAbsolutePer2) {
        AiFiAbsolutePer2 = aiFiAbsolutePer2;
    }
}
