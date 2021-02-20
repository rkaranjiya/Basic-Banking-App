package com.example.thesparkbank;

public class HistoryModel {

    String tx1, tx2,tx4;
    Double tx3;

    public HistoryModel() {

    }

    public HistoryModel(String tx1, String tx2, String tx4, Double tx3) {
        this.tx1 = tx1;
        this.tx2 = tx2;
        this.tx4 = tx4;
        this.tx3 = tx3;
    }

    public String getTx1() {
        return tx1;
    }

    public void setTx1(String tx1) {
        this.tx1 = tx1;
    }

    public String getTx2() {
        return tx2;
    }

    public void setTx2(String tx2) {
        this.tx2 = tx2;
    }

    public String getTx4() {
        return tx4;
    }

    public void setTx4(String tx4) {
        this.tx4 = tx4;
    }

    public Double getTx3() {
        return tx3;
    }

    public void setTx3(Double tx3) {
        this.tx3 = tx3;
    }
}
