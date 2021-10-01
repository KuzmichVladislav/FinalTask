/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.company.gum.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Vladislav Kuzmich
 */
class ValidatorTest {

    public ValidatorTest() {
    }

    /**
     * Test of checkLogin method, of class Validator.
     */
    @Test
    void testCheckLogin() {
        String login = "Cameron";
        boolean expResult = true;
        boolean result = Validator.checkLogin(login);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkPassword method, of class Validator.
     */
    @Test
    void testCheckPassword() {
        String password = "Tdbgvs33";
        boolean expResult = true;
        boolean result = Validator.checkPassword(password);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkNameSurname method, of class Validator.
     */
    @Test
    void testCheckNameSurname() {
        String nameSurname = "Vlad";
        boolean expResult = true;
        boolean result = Validator.checkNameSurname(nameSurname);
        Assertions.assertEquals(expResult, result);
    }

    /**
     * Test of checkPhone method, of class Validator.
     */
    @Test
    void testCheckPhone() {
        String phone = "9379992";
        boolean expResult = true;
        boolean result = Validator.checkPhone(phone);
        Assertions.assertEquals(expResult, result);
    }

    /**
     * Test of checkMail method, of class Validator.
     */
    @Test
    void testCheckMail() {
        String mail = "cameron@mail.ru";
        boolean expResult = true;
        boolean result = Validator.checkMail(mail);
        Assertions.assertEquals(expResult, result);
    }

    /**
     * Test of checkMoney method, of class Validator.
     */
    @Test
    void testCheckMoney() {
        String money = "93.4";
        boolean expResult = true;
        boolean result = Validator.checkMoney(money);
        Assertions.assertEquals(expResult, result);

    }

    /**
     * Test of checkDiscount method, of class Validator.
     */
    @Test
    void testCheckDiscount() {
        String discount = "30";
        boolean expResult = true;
        boolean result = Validator.checkDiscount(discount);
        Assertions.assertEquals(expResult, result);
    }

}
