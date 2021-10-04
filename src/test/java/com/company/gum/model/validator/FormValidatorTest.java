package com.company.gum.model.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Class FormValidatorTest.
 *
 * @author Vladislav Kuzmich
 */
class FormValidatorTest {

    /**
     * Test of checkLogin method, of class FormValidator.
     */
    @Test
    void testCheckLogin() {
        String login = "Login";
        boolean result = FormValidator.checkLogin(login);
        Assertions.assertTrue(result);
    }

    /**
     * Test of checkPassword method, of class FormValidator.
     */
    @Test
    void testCheckPassword() {
        String password = "Password22";
        boolean result = FormValidator.checkPassword(password);
        Assertions.assertTrue(result);
    }

    /**
     * Test of checkNameSurname method, of class FormValidator.
     */
    @Test
    void testCheckNameSurname() {
        String nameSurname = "Obama";
        boolean result = FormValidator.checkNameSurname(nameSurname);
        Assertions.assertTrue(result);
    }

    /**
     * Test of checkPhone method, of class FormValidator.
     */
    @Test
    void testCheckPhone() {
        String phone = "+375259379992";
        boolean result = FormValidator.checkPhone(phone);
        Assertions.assertTrue(result);
    }

    /**
     * Test of checkMail method, of class FormValidator.
     */
    @Test
    void testCheckMail() {
        System.out.println("checkMail");
        String mail = "obami@example.com";
        boolean result = FormValidator.checkMail(mail);
        Assertions.assertTrue(result);
    }

    /**
     * Test of checkMoney method, of class FormValidator.
     */
    @Test
    void testCheckMoney() {
        String money = "99.85";
        boolean result = FormValidator.checkMoney(money);
        Assertions.assertTrue(result);
    }

    /**
     * Test of checkDiscount method, of class FormValidator.
     */
    @Test
    void testCheckDiscount() {
        String discount = "10";
        boolean result = FormValidator.checkDiscount(discount);
        Assertions.assertTrue(result);
    }
}
