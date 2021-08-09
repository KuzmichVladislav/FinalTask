package com.company.gum.main;

import com.company.gum.dao.impl.UserDaoImpl;
import com.company.gum.entity.userImpl.User;
import com.company.gum.exception.DaoException;

public class Main {
    public static void main(String[] args) {
        User user = null;

        try {
            user = UserDaoImpl.getInstance().findById(1);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        System.out.println(user);

        try {
            user = UserDaoImpl.getInstance().findByLogin("client");
        } catch (DaoException e) {
            e.printStackTrace();
        }

        System.out.println(user);


       UserDaoImpl userDao = (UserDaoImpl) UserDaoImpl.getInstance();

        try {
            userDao.delete(2);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        System.out.println(user);


    }

}
