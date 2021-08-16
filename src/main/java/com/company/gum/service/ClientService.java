package com.company.gum.service;

import com.company.gum.entity.Client;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {
    Client createClient(Client client) throws ServiceException;

    boolean updateClient(Client client);

    boolean verification(int clientId);

    boolean refillMoney(int clientId, BigDecimal amount);

    boolean withdrawMoney(int clientId, BigDecimal amount);

    Client findClientById(int clientId);

    List<Client> findAllClient();

    List<Client> findAllActiveClient();

    List<Client> findAllClientByAnthroponym(String name, String surname);
}
