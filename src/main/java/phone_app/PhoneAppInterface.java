package phone_app;

public interface PhoneAppInterface {

    void registerUser(String phone, String password, int cardnumber);
    void enterInApp();
    void enterPhoneNumber();
    void enterPassword();
    void enterCardNumber();
    void checkCardNumber();
}
