package com.company.gum.model.service.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.dao.ClientDao;
import com.company.gum.model.dao.impl.ClientDaoImpl;
import com.company.gum.model.entity.Client;
import com.company.gum.model.service.ClientService;
import com.company.gum.model.util.JBCryptPasswordEncoder;
import com.company.gum.model.util.MailSender;

import java.math.BigDecimal;

/**
 * The Class ClientServiceImpl.
 *
 * @author Vladislav Kuzmich
 */
public class ClientServiceImpl implements ClientService {

    /**
     * The instance.
     */
    private static ClientServiceImpl instance;

    /**
     * The client dao.
     */
    private final ClientDao clientDao = ClientDaoImpl.getInstance();

    /**
     * Instantiates a new client service impl.
     */
    private ClientServiceImpl() {
    }

    /**
     * Gets the single instance of ClientServiceImpl.
     *
     * @return single instance of ClientServiceImpl
     */
    public static ClientServiceImpl getInstance() {
        if (instance == null) {
            instance = new ClientServiceImpl();
        }
        return instance;
    }

    /**
     * Creates the client.
     *
     * @param client the client
     * @return the client
     * @throws ServiceException the service exception
     */
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
        sender.send(client.getMail(),
                MAIL_MESSAGE_PART_1 + client.getId() + MAIL_MESSAGE_PART_2);
        return createdClient;
    }

    /**
     * Edits the client.
     *
     * @param client the client
     * @return true, if successful
     * @throws ServiceException the service exception
     */
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

    /**
     * Verification.
     *
     * @param clientId the client id
     * @return true, if successful
     * @throws ServiceException the service exception
     */
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

    /**
     * Refill money.
     *
     * @param clientId the client id
     * @param amount   the amount
     * @return true, if successful
     * @throws ServiceException the service exception
     */
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

    /**
     * Withdraw money.
     *
     * @param clientId the client id
     * @param amount   the amount
     * @return true, if successful
     * @throws ServiceException the service exception
     */
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

    /**
     * Assign discount.
     *
     * @param clientId the client id
     * @param discount the discount
     * @return true, if successful
     * @throws ServiceException the service exception
     */
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

    /**
     * Find client by id.
     *
     * @param clientId the client id
     * @return the client
     * @throws ServiceException the service exception
     */
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
}
