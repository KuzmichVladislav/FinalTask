package com.company.gum.service;

import com.company.gum.entity.Client;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    Client createClient(Client client) throws ServiceException;

    boolean updateClient(Client client) throws ServiceException;

    boolean verification(int clientId) throws ServiceException;

    boolean refillMoney(int clientId, BigDecimal amount) throws ServiceException;

    boolean withdrawMoney(int clientId, BigDecimal amount) throws ServiceException;

    Client findClientById(int clientId) throws ServiceException;

    List<Client> findAllClient() throws ServiceException;

    List<Client> findAllActiveClient() throws ServiceException;

    List<Client> findAllClientByAnthroponym(String name, String surname) throws ServiceException;
}
