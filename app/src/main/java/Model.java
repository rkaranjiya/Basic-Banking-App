package com.example.thesparkbank;

public class Model {

    private int ImageView;
    private String tx1,tx2;
    private Double tx3;

    public Model() {

    }

    public Model(int imageView, String tx1, String tx2, Double tx3) {
        ImageView = imageView;
        this.tx1 = tx1;
        this.tx2 = tx2;
        this.tx3 = tx3;
    }

    public int getImageView() {
        return ImageView;
    }

    public void setImageView(int imageView) {
        ImageView = imageView;
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

    public Double getTx3() {
        return tx3;
    }

    public void setTx3(Double tx3) {
        this.tx3 = tx3;
    }
}
