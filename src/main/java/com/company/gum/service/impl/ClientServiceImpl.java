package com.company.gum.service.impl;

import com.company.gum.dao.ClientDao;
import com.company.gum.dao.impl.ClientDaoImpl;
import com.company.gum.entity.Client;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.ClientService;
import com.company.gum.util.JBCryptPasswordEncoder;
import com.company.gum.util.MailSender;

import java.math.BigDecimal;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    private static ClientServiceImpl instance;

    private ClientDao clientDao = ClientDaoImpl.getInstance();

    private ClientServiceImpl() {
    }

    public static ClientServiceImpl getInstance() {
        if (instance == null) {
            instance = new ClientServiceImpl();
        }
        return instance;
    }

    @Override
    public Client createClient(Client client) throws ServiceException {
        Client createdClient;
        try {
            client.setPassword(JBCryptPasswordEncoder.encode(client.getPassword()));
            createdClient = clientDao.createClient(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        MailSender sender = new MailSender();
        sender.send(client.getId(), client.getMail(),
                MAIL_MESSAGE_PART_1 + client.getId() + MAIL_MESSAGE_PART_2);
        return createdClient;
    }

    @Override
    public boolean editClient(Client client) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.editClient(client);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean verification(int clientId) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.verification(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean refillMoney(int clientId, BigDecimal amount) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.refillMoney(clientId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean withdrawMoney(int clientId, BigDecimal amount) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.withdrawMoney(clientId, amount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean assignDiscount(int clientId, BigDecimal discount) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = clientDao.assignDiscount(clientId, discount);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public Client findClientById(int clientId) throws ServiceException {
        Client client;
        try {
            client = clientDao.findClientById(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return client;
    }

    @Override
    public List<Client> findAllClient() throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAllClient();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }

    @Override
    public List<Client> findAllActiveClient() throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAllActiveClient();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }

    @Override
    public List<Client> findAllClientByAnthroponym(String name, String surname) throws ServiceException {
        List<Client> clients;
        try {
            clients = clientDao.findAllClientByAnthroponym(name, surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return clients;
    }
}
