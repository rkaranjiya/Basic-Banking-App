package com.example.thesparkbank;

public class Customer {

    private int dp;
    private String name;

    public Customer() {
    }

    public Customer(int dp, String name) {
        this.dp = dp;
        this.name = name;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

