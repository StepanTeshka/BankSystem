package atm;
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

    void registerUser(String phone, String password, int cardnumber);

    void ask();

}