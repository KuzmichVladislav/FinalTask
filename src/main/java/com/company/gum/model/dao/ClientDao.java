package com.company.gum.model.dao;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Client;

import java.math.BigDecimal;

/**
 * The Interface ClientDao.
 *
 * @author Vladislav Kuzmich
 */
public interface ClientDao {

    /**
     * The image src prefix.
     */
    String IMAGE_SRC_PREFIX = "data:image/jpg;base64,";

    /**
     * The default image.
     */
    String DEFAULT_IMAGE = "https://i.ibb.co/mDTmCZn/png-transparent-computer-icons-user-profile-user-avatar-blue-heroes-electric-blue.png";

    /**
     * Creates the client.
     *
     * @param client the client
     * @return the client
     * @throws DaoException the dao exception
     */
    Client createClient(Client client) throws DaoException;

    /**
     * Edits the client.
     *
     * @param client the client
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean editClient(Client client) throws DaoException;

    /**
     * Verification.
     *
     * @param clientId the client id
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean verification(int clientId) throws DaoException;

    /**
     * Refill money.
     *
     * @param clientId the client id
     * @param amount   the amount
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean refillMoney(int clientId, BigDecimal amount) throws DaoException;

    /**
     * Withdraw money.
     *
     * @param clientId the client id
     * @param amount   the amount
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean withdrawMoney(int clientId, BigDecimal amount) throws DaoException;

    /**
     * Assign discount.
     *
     * @param clientId the client id
     * @param discount the discount
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean assignDiscount(int clientId, BigDecimal discount) throws DaoException;

    /**
     * Find client by id.
     *
     * @param clientId the client id
     * @return the client
     * @throws DaoException the dao exception
     */
    Client findClientById(int clientId) throws DaoException;

}
