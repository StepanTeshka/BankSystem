package phone_app;

import atm.*;

import user.*;

import org.example.CheckTypeInput;

import java.util.ArrayList;

public class PhoneApp implements PhoneAppInterface {
    public ArrayList<BankCard> cards = new ArrayList<BankCard>();
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    long phoneNumber;
    String password;

    int currentCardNumber;

    BankUser currentUser;

    BankCard bankCard;

    @Override
    public void registerUser(String phone, String password, int cardnumber) {

    }

    @Override
    public void enterInApp() {
        enterPhoneNumber();
    }


    @Override
    public void enterPhoneNumber() {
        System.out.print("Введите номер телефона: ");
        phoneNumber = CheckTypeInput.longe();
        if (phoneNumber == -1) {
            System.out.println(phoneNumber);
            System.out.println("");
            System.out.println("Ввели не правильный тип данных");
            enterPhoneNumber();
        } else enterPassword();
    }

    @Override
    public void enterPassword() {
        System.out.println("");
        System.out.print("Введите пароль:");
        password = CheckTypeInput.string();
        if (password.equals("-")) {
            System.out.println("");
            System.out.println("Ввели не правильный тип данных");
            enterPassword();
        } else enterCardNumber();
    }

    @Override
    public void enterCardNumber() {
        System.out.println("");
        System.out.print("Введите номер карты:");
        currentCardNumber = CheckTypeInput.integer();
        if (currentCardNumber == -1) {
            System.out.println("");
            System.out.println("Ввели не правильный тип данных");
            enterCardNumber();
        } else checkCardNumber();
    }

    @Override
    public void checkCardNumber() {
        BankATM bank = new BankATM();
        boolean isFind = false;
        cards = bank.cards;
        bankAccounts = bank.bankAccounts;
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println(i);
            if (bankAccounts.get(i).getUserCard().getCardNumber() == currentCardNumber) {
                currentUser = bankAccounts.get(i);
                bankAccounts.get(i).getUserCard().setPhoneNumber(phoneNumber);
                bankAccounts.get(i).getUserCard().setPassword(password);

                bankCard = cards.get(i);
                isFind = true;
            }
        }
        if(isFind) {
            bankCard.setPhoneNumber(phoneNumber);
            System.out.println("Вы успешно зарегистрировались!");
            System.out.printf("Ваш телефон: +%d \n Ваш пароль: %s \n Ваше имя: %s ", phoneNumber, password, currentUser.getUserName());
        } else {
            System.out.println("Карта не найдена(");
            enterCardNumber();
        }
    }
}
