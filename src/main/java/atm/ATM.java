package atm;
import user.BankCard;
import user.BankUser;

public interface ATM {
    void insertCard(int cardNumber);

    boolean enterPin(int pin);

    BankUser getCurrentUser();

    double checkBalance();

    void deposit(double amount);

    void withdraw(double amount);

    void ejectCard();

    void blockCard();

    void registerUser(long phone, String password, BankCard card);

    void ask();

}