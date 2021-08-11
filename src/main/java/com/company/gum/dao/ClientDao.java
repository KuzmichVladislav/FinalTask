package com.company.gum.dao;

import com.company.gum.entity.user_impl.Client;
import com.company.gum.exception.DaoException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientDao {

    Client createClient(Client client) throws DaoException;

    boolean updateClient(Client client) throws DaoException;

    boolean verification(int clientId) throws DaoException;

    boolean refillMoney(int clientId, BigDecimal amount) throws DaoException;

    boolean withdrawMoney(int clientId, BigDecimal amount) throws DaoException;

    Client findClientById(int clientId) throws DaoException;

    List<Client> findAllClient() throws DaoException;

    List<Client> findAllActiveClient() throws DaoException;

    List<Client> findAllClientByAnthroponym(String name, String surname) throws DaoException;

}
