package com.company.gum.dao;

import com.company.gum.entity.user_impl.Client;
import com.company.gum.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientDao {

    Client create(Client client) throws DaoException;

    boolean update(Client client) throws DaoException;

    boolean verification(int clientId) throws DaoException;

    boolean refill(int clientId, BigDecimal amount) throws DaoException;

    boolean withdrawal(int clientId, BigDecimal amount) throws DaoException;

    Client findClientById(int clientId) throws DaoException;

    List<Client> findAllClient();

    List<Client> findAllActiveClient();

    List<Client> findAllActiveClientByName(String name, String surname);

}
