package com.company.gum.service.impl;

import com.company.gum.dao.ClientDao;
import com.company.gum.dao.impl.ClientDaoImpl;
import com.company.gum.entity.Client;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.util.passworEncoder.PasswordEncoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger();
    private static ClientService clientService = new ClientServiceImpl();

    private ClientDao clientDao = ClientDaoImpl.getInstance();

    private ClientServiceImpl() {
    }

    public static ClientService getInstance() {
        return clientService;
    }


    @Override
    public Client createClient(Client client) throws ServiceException {
        Client createdClient;
        try {
            client.setPassword(PasswordEncoder.encode(client.getPassword()));
            createdClient = clientDao.createClient(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return createdClient;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }

    @Override
    public boolean verification(int clientId) {
        return false;
    }

    @Override
    public boolean refillMoney(int clientId, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean withdrawMoney(int clientId, BigDecimal amount) {
        return false;
    }

    @Override
    public Client findClientById(int clientId) {
        return null;
    }

    @Override
    public List<Client> findAllClient() {
        return null;
    }

    @Override
    public List<Client> findAllActiveClient() {
        return null;
    }

    @Override
    public List<Client> findAllClientByAnthroponym(String name, String surname) {
        return null;
    }
}
