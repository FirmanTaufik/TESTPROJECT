package com.test_jsoup.app.LEASTSQUARE;

public class TabelModel {
    //y = penjualan
    //x = waktu
    private String bulan;
    private int  y, x, xy,xKuadrat;


    public TabelModel(String bulan, int y, int x, int xy, int xKuadrat) {
        this.bulan = bulan;
        this.y = y;
        this.x = x;
        this.xy = xy;
        this.xKuadrat = xKuadrat;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getXy() {
        return xy;
    }

    public void setXy(int xy) {
        this.xy = xy;
    }

    public int getxKuadrat() {
        return xKuadrat;
    }

    public void setxKuadrat(int xKuadrat) {
        this.xKuadrat = xKuadrat;
    }
}
