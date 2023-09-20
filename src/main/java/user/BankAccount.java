package user;

import user.BankCard;
import user.BankUser;

public class BankAccount implements BankUser {
    private String userName;
    private int accountId;
    private double accountBalance;
    private BankCard userCard;

    private long phoneNumber;

    private String password;

    public BankAccount(String userName, int accountId, double accountBalance, BankCard userCard, long phoneNumber, String password) {
        this.userName = userName;
        this.accountId = accountId;
        this.accountBalance = accountBalance;
        this.userCard = userCard;
        this.phoneNumber = phoneNumber;
        this.password = password;

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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
