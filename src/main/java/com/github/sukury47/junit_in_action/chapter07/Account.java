package com.github.sukury47.junit_in_action.chapter07;

public class Account {
    private String userId;
    private long balance;

    public Account(String userId, long balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public void debit(long amount) {
        this.balance -= amount;
    }

    public void credit(long amount) {
        this.balance += amount;
    }

    public long getBalance() {
        return balance;
    }

    public String getUserId() {
        return userId;
    }
}