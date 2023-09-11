package org.example;

public interface BankUser {
    String getUserName();
    int getAccountId();
    double getAccountBalance();
    void setAccountBalance(double accountBalance);
    BankCard getUserCard();
}
