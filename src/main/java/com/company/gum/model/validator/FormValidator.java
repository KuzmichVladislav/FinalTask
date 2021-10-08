package com.company.gum.model.validator;

public class FormValidator {

    private static final String LOGIN_REGEX = "^[\\w_]{3,16}$";

    private static final String PASSWORD_REGEX = "^[\\w_]{6,18}$";

    private static final String NAME_SURNAME_REGEX = "^[\\p{IsAlphabetic}\\-]{2,20}$";

    private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";

    private static final String MAIL_REGEX = "^([\\w_\\.-]+)@([\\w_\\.-]+)\\.([a-z\\.]{2,6})$";

    private static final String MONEY_REGEX = "^[0-9]{1,3}(\\.[0-9]{1,2})?$";

    private static final String DISCOUNT_REGEX = "^[0-9]{1,2}(\\.[0-9]{1,2})?$";

    private static FormValidator instance;

    private FormValidator() {
    }

    public static FormValidator getInstance() {
        if (instance == null) {
            instance = new FormValidator();
        }
        return instance;
    }

    public boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public boolean checkNameSurname(String nameSurname) {
        return nameSurname.matches(NAME_SURNAME_REGEX);
    }

    public boolean checkPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public boolean checkMail(String mail) {
        return mail.matches(MAIL_REGEX);
    }

    public boolean checkMoney(String money) {
        return money.matches(MONEY_REGEX);
    }

    public boolean checkDiscount(String discount) {
        return discount.matches(DISCOUNT_REGEX);
    }

}
