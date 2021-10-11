package com.company.gum.model.service;

import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Client;

import java.math.BigDecimal;

/**
 * The Interface ClientService.
 *
 * @author Vladislav Kuzmich
 */
public interface ClientService {

	/**
	 * The mail message part 1.
	 */
	String MAIL_MESSAGE_PART_1 = "Click on this link to verify your account: <a href='http://localhost:8080/gum/controller?command=verification&userId=";

	/**
	 * The mail message part 2.
	 */
	String MAIL_MESSAGE_PART_2 = ">verification</a>";

	/**
	 * Creates the client.
	 *
	 * @param client the client
	 * @return the client
	 * @throws ServiceException the service exception
	 */
	Client createClient(Client client) throws ServiceException;

	/**
	 * Edits the client.
	 *
	 * @param client the client
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean editClient(Client client) throws ServiceException;

	/**
	 * Verification.
	 *
	 * @param clientId the client id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean verification(int clientId) throws ServiceException;

	/**
	 * Refill money.
	 *
	 * @param clientId the client id
	 * @param amount   the amount
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean refillMoney(int clientId, BigDecimal amount) throws ServiceException;

	/**
	 * Withdraw money.
	 *
	 * @param clientId the client id
	 * @param amount   the amount
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean withdrawMoney(int clientId, BigDecimal amount) throws ServiceException;

	/**
	 * Assign discount.
	 *
	 * @param clientId the client id
	 * @param discount the discount
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean assignDiscount(int clientId, BigDecimal discount) throws ServiceException;

	/**
	 * Find client by id.
	 *
	 * @param clientId the client id
	 * @return the client
	 * @throws ServiceException the service exception
	 */
	Client findClientById(int clientId) throws ServiceException;
}
