package com.company.gum.main;

import com.company.gum.dao.impl.ClientDaoImpl;
import com.company.gum.dao.impl.UserDaoImpl;
import com.company.gum.entity.user_impl.Client;
import com.company.gum.exception.DaoException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {
        UserDaoImpl.getInstance().restoreUser(4);
        Client client = new Client();

        client.setLogin("firstLogin");
        client.setPassword("aloha");
        client.setName("Ivan");
        client.setSurname("Ivanov");
        client.setMail("123@jas.by");
        client.setPhone("9379992");
        Client client2 = ClientDaoImpl.getInstance().create(client);

    }

}
