package org.example;

public class BankAccount implements BankUser {
    private String userName;
    private int accountId;
    private double accountBalance;
    private BankCard userCard;
    public BankAccount(String userName, int accountId, double accountBalance, BankCard userCard) {
        this.userName = userName;
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.userCard = userCard;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public int getAccountId() {
        return accountId;
    }

    @Override
    public double getAccountBalance() {
        return accountBalance;
    }
    @Override
    public BankCard getUserCard() {
        return userCard;
    }
    @Override
    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
