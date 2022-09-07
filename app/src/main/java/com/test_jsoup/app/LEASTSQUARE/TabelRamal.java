package com.test_jsoup.app.LEASTSQUARE;

public class TabelRamal {
    private String BlnThn;
    private double x;

    public TabelRamal(String blnThn, double x) {
        BlnThn = blnThn;
        this.x = x;
    }

    public String getBlnThn() {
        return BlnThn;
    }

    public void setBlnThn(String blnThn) {
        BlnThn = blnThn;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}
