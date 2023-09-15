package org.example;

import java.util.ArrayList;
import java.util.Scanner;
public class BankATM implements ATM {
    Scanner sc = new Scanner(System.in);
    private BankCard currentCard;
    private BankAccount currentUser;
    private int remainingPinAttempts;
    private boolean next = false;
    public ArrayList<BankCard> cards = new ArrayList<BankCard>();
    public ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    public BankCard card1 = new BankCard(123456,1234,123);
    public BankCard card2 = new BankCard(654321,4321,321);
    public BankAccount user1 = new BankAccount("Stepan",1,1234, card1);
    public BankAccount user2 = new BankAccount("Slayik",2,4321, card2);
    CheckTypeInput cti = new CheckTypeInput();
    private int checkMethod() {
        System.out.println("Что бы посмотреть баланс введите 1");
        System.out.println("Что бы положить деньги введите 2");
        System.out.println("Что бы снять деньги введите 3");
        System.out.println("Что бы выйти с терминала введите 4");
        int method = cti.integer();
        return method;
    }
    private int checkPinCode() {
        System.out.println("Введите пин-код:");
        int pinCode = cti.integer();
        return pinCode;
    }
    private double checkMethodsTwo() {
        System.out.println("Введите сумму которую хотите положить:");
        double sum = sc.nextDouble();
        return sum;
    }
    private double checkMethodsThree() {
        System.out.println("Введите сумму которую хотите снять:");
        double sum = sc.nextDouble();
        return sum;
    }
    public void terminate() {
            cards.add(card1);
            cards.add(card2);
            bankAccounts.add(user1);
            bankAccounts.add(user2);
            System.out.println("Введите номер карты:");
            int card = cti.integer();
            if (card > 0) {
                insertCard(card);
                next = true;
            } else if (card == -1) {
                System.out.println("Вы ввели не правильный тип данных");
                terminate();
            }

            for (int i = 0; i <= remainingPinAttempts; i++) {
                int pinCode = checkPinCode();
                boolean x = false;
                if(pinCode > 0) {
                    x = enterPin(pinCode);
                }
                if (x == true) {
                    while (currentCard != null) {
                        int method = checkMethod();
                        if(method == -1){
                            System.out.println("Вы ввели не правильный тип данных");
                            method = checkMethod();
                        }
                        if (method == 1) {
                            System.out.println(checkBalance());
                        } else if (method == 2) {
                            double sum = checkMethodsTwo();
                            if(sum == -1){
                                System.out.println("Вы ввели не правильный тип данных");
                                sum = checkMethodsTwo();
                            }else {
                                deposit(sum);
                            }
                        } else if (method == 3) {
                            double sum = checkMethodsThree();
                            if(sum == -1){
                                System.out.println("Вы ввели не правильный тип данных");
                                sum = checkMethodsThree();
                            }else {
                                withdraw(sum);
                            }
                        } else if (method == 4) {
                            System.out.println("Вы вышли из терминала");
                            ejectCard();
                        }
                    }
                    return;
                }else {
                    System.out.println("Вы ввели не правильный тип данных");
                    pinCode = checkPinCode();
            }
        }
    }
    @Override
    public void insertCard(int cardNumber) {
        for(int i = 0; i < cards.size(); i++) {
            if(cards.get(i).getCardNumber() == cardNumber){
                currentCard = cards.get(i);
            };
        }
        remainingPinAttempts = 3;
    }

    @Override
    public boolean enterPin(int pin) {
        if (currentCard != null && pin == currentCard.getPinCode() && remainingPinAttempts > 0) {
            for(int i =0; i < bankAccounts.size(); i++) {
                if (bankAccounts.get(i).getUserCard() == currentCard){
                    currentUser = bankAccounts.get(i);
                    System.out.println("Вы успешно вошли");
                    getCurrentUser();
                }
            }
            return true;
        } else {
            remainingPinAttempts--;
            if (remainingPinAttempts == 0) {
                blockCard();
            }
            return false;
        }
    }

    @Override
    public BankUser getCurrentUser() {
        System.out.println("Имя: " + currentUser.getUserName());
        return currentUser;
    }

    @Override
    public double checkBalance() {
        if (currentUser != null) {
            return currentUser.getAccountBalance();
        }
        return 0.0;
    }

    @Override
    public void deposit(double amount) {
        double x =currentUser.getAccountBalance();
        if (amount > 0) {
            currentUser.setAccountBalance(x += amount);
            System.out.println("Успешно положили " + amount + "У вас на счету стало: " + x);
        }else {
            System.out.println("Вы ввели не допустимую сумму");
        }
    }

    @Override
    public void withdraw(double amount) {
        double x = currentUser.getAccountBalance();
        if (amount > 0 && amount <= x) {

                currentUser.setAccountBalance(x -= amount);
                System.out.println("Успешно сняли " + amount + "У вас на счету стало: " + x);
            }else {
            System.out.println("Вы ввели не допустимую сумму");
        }
    }

    @Override
    public void ejectCard() {
        currentCard = null;
        currentUser = null;
    }

    @Override
    public void blockCard() {
        System.out.println("Вы заблакированны");
        currentCard.setIsBlocked(true);
    }

    @Override
    public void registerUser() {

    }
}