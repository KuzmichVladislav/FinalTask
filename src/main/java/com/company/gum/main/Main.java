package com.company.gum.main;

import com.company.gum.dao.impl.ClientDaoImpl;
import com.company.gum.dao.impl.UserDaoImpl;
import com.company.gum.entity.user_impl.Client;
import com.company.gum.exception.DaoException;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, DaoException {

        Client client = new Client();

        client.setLogin("firstLogin");
        client.setPassword("aloha");
        client.setName("Pert");
        client.setSurname("Petrov");
        client.setMail("456@jas.by");
        client.setPhone("9379993");
        ClientDaoImpl.getInstance().createClient(client);

       // ClientDaoImpl.getInstance().withdrawal(1, new BigDecimal(5500.5));

    }

}
