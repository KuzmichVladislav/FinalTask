package com.company.gum.util;

public class Validator {

    private static final String LOGIN_REGEX = "^[\\w_]{3,16}$";
    private static final String PASSWORD_REGEX = "^[\\w_]{6,18}$";
    private static final String NAME_SURNAME_REGEX = "^[\\p{IsAlphabetic}\\-]{3,20}$";
    private static final String PHONE_REGEX = "^\\+?\\d{7,20}$";
    private static final String MAIL_REGEX = "^([\\w_\\.-]+)@([\\w_\\.-]+)\\.([a-z\\.]{2,6})$";
    private static final String MONEY_REGEX = "^[0-9]{1,3}(\\.[0-9]{1,2})?$";


    private Validator() {
    }

    public static boolean checkLogin(String login) {
        return login.matches(LOGIN_REGEX);
    }

    public static boolean checkPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean checkNameSurname(String nameSurname) {
        return nameSurname.matches(NAME_SURNAME_REGEX);
    }

    public static boolean checkPhone(String phone) {
        return phone.matches(PHONE_REGEX);
    }

    public static boolean checkMail(String mail) {
        return mail.matches(MAIL_REGEX);
    }

    public static boolean checkMoney(String money) {
        return money.matches(MONEY_REGEX);
    }


}
