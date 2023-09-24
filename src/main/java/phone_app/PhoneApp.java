package phone_app;

import atm.*;

import user.*;

import org.example.CheckTypeInput;
import java.util.Calendar;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class PhoneApp implements PhoneAppInterface {
    public ArrayList<BankCard> cards = new ArrayList<BankCard>();
    private long phoneNumber;
    private String password;
    public BankATM bank;
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public int currentCardNumber;

    public BankAccount currentUser;
    public BankAccount anotherUser;

    private BankCard bankCard;

    public PhoneApp(BankATM bank){
        this.bank = bank;
        bankAccounts = bank.bankAccounts;
        cards = bank.cards;
    }

    @Override
    public void registerUser(String phone, String password, int cardnumber) {

    }

    @Override
    public void enterInApp() {
        System.out.println("Войти-1");
        System.out.println("Зарегистрироваться-2");
        int command = CheckTypeInput.integer();
        if(command != -1){
            if(command == 1) enterPhoneNumber(false);
            if(command == 2) enterPhoneNumber(true);
        }
        else {
            System.out.println("Неверная команда!");
            enterInApp();
        }
    }


    @Override
    public void enterPhoneNumber(boolean isReg) {
            System.out.print("Введите номер телефона: ");
            phoneNumber = CheckTypeInput.longe();
            if (phoneNumber == -1) {
                System.out.println("");
                System.out.println("Ввели не правильный тип данных");
                enterPhoneNumber(isReg);
            } else {
                if(!isReg){
                        if(!findUserPhone(phoneNumber)){
                            System.out.println("Пользователь не найден!");
                            enterPhoneNumber(isReg);
                        } else {
                            enterPassword(isReg);
                        }
            } else enterPassword(isReg);
        }
    }

    @Override
    public void enterPassword(boolean isReg) {
        System.out.println("");
        System.out.print("Введите пароль:");
        password = CheckTypeInput.string();
        if (password.equals("-")) {
            System.out.println("");
            System.out.println("Ввели не правильный тип данных");
            enterPassword(isReg);
        } else {
            if(isReg) {
                enterCardNumber(true);
            }else {
                if (currentUser.getPassword().equals(password)){
                    printInfo(true, false);
                    } else {
                    System.out.println("Ввели не правильный пароль");
                    enterPassword(isReg);
                    }
                }
            }
        }

    @Override
    public void enterCardNumber(boolean isRegister) {
        System.out.println("");
        System.out.print("Введите номер карты:");
        currentCardNumber = CheckTypeInput.integer();
        if (currentCardNumber == -1) {
            System.out.println("");
            System.out.println("Ввели не правильный тип данных");
            enterCardNumber(false);
        } else checkCardNumber(isRegister);
    }

    @Override
    public void checkCardNumber(boolean isRegister) {
        boolean isFind = false;
        for (int i = 0; i < bankAccounts.size(); i++) {
            System.out.println(i);
            if (bankAccounts.get(i).getUserCard().getCardNumber() == currentCardNumber) {
                currentUser = bankAccounts.get(i);
                bankAccounts.get(i).getUserCard().setPhoneNumber(phoneNumber);
                bankAccounts.get(i).getUserCard().setPassword(password);
                bankCard = cards.get(i);
                isFind = true;
                break;
            }
        }
        printInfo(isFind, isRegister);
    }

    @Override
    public void printInfo(boolean isFind, boolean isRegister) {
        if(isFind) {
            if(isRegister){

            }
            System.out.println("Вы успешно зарегистрировались/вошли!");
            System.out.printf("Ваш телефон: +%d \n Ваш пароль: %s \n Ваше имя: %s ", phoneNumber, password, currentUser.getUserName());
            consoleInterface();
        } else {
            System.out.println("Карта не найдена(");
            enterCardNumber(isRegister);
        }
    }

    @Override
    public boolean findUserPhone(long phoneNumber) {
        boolean isFind = false;
        bankAccounts = bank.bankAccounts;
        for (int i = 0; i < bankAccounts.size(); i++){
            if(bankAccounts.get(i).getPhoneNumber() == phoneNumber){
                currentUser = bankAccounts.get(i);
                isFind = true;
                break;
            }
        }
        if(isFind){
            return true;
        } else return false;
    }


    @Override
    public void checkUserPassword(BankUser user) {

    }

    @Override
    public void exiteApp() {
        currentUser = null;
        password = null;
        bankAccounts = null;
        bank.ask();
    }

    @Override
    public void deposit(double amount) {
            System.out.print("Напишите сумму: ");
            amount = CheckTypeInput.Double();
            if (amount != -1){
            double x = currentUser.getAccountBalance();
            if (amount > 0) {
                currentUser.setAccountBalance(x += amount);
                System.out.println("Успешно положили " + amount + "У вас на счету стало: " + x);
            }else {
                System.out.println("Вы ввели не допустимую сумму");
            }
        } else {
                System.out.println("Ввели не тот тип данных");
                consoleInterface();
            }
    }

    @Override
    public void withdraw(double amount) {
        System.out.print("Номер телефона для перевода: ");
        long anoterPhoneNumber = CheckTypeInput.integer();
        if (anoterPhoneNumber == -1) {
            System.out.println("Ввели не тот тип данных");
            withdraw(0);
        } else {
            if(findAnotherUserPhone(anoterPhoneNumber)){
            System.out.print("Напишите сумму перевода: ");
            amount = CheckTypeInput.Double();
            if (amount != -1) {
                double x = anotherUser.getAccountBalance();
                if (amount > 0 && amount <= x) {
                    anotherUser.setAccountBalance(x -= amount);
                    System.out.println("Успешно перевели " + amount + "У вас на счету стало: " + x);
                    currentUser.addTransferHistory(new TransferHistory(amount, anoterPhoneNumber, LocalDate.now(), LocalTime.now(), LocalDate.now().getDayOfWeek(), true, Calendar.getInstance().getTimeZone().toZoneId()));
                    anotherUser.addTransferHistory(new TransferHistory(amount, phoneNumber, LocalDate.now(), LocalTime.now(), LocalDate.now().getDayOfWeek(), false,Calendar.getInstance().getTimeZone().toZoneId()));
                } else {
                    System.out.println("Вы ввели не допустимую сумму");
                }
            } else {
                System.out.println("Ввели не тот тип данных");
                consoleInterface();
                }
            } else {
                System.out.println("Пользователь не найден с таким номером телефона");
                withdraw(0);
            }
        }
    }

    @Override
    public void consoleInterface() {
        int command;
        while (true){
            System.out.println("Выбирите действие: \n 1-выйти \n 2-перевести деньги \n 3-пополнить \n 4-история переводов \n");
            command = CheckTypeInput.integer();
            if(command == -1) System.out.println("Вы ввели не правильный тип данных");
            else {
                switch (command) {
                    case 1 -> exiteApp();
                    case 2 -> withdraw(30);
                    case 3 -> deposit(30);
                    case 4 -> printHistory();
                }
            }
        }
    }

    @Override
    public void printHistory() {
        currentUser.printTransferHistory();
    }

    @Override
    public boolean findAnotherUserPhone(long phoneNumber) {
        boolean isFind = false;
        bankAccounts = bank.bankAccounts;
        for (int i = 0; i < bankAccounts.size(); i++){
            if(bankAccounts.get(i).getPhoneNumber() == phoneNumber){
                anotherUser = bankAccounts.get(i);
                isFind = true;
                break;
            }
        }
        if(isFind){
            return true;
        } else return false;
    }

}
