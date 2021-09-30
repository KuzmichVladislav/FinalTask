package com.company.gum.service;

import com.company.gum.entity.Client;
import com.company.gum.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    String MAIL_MESSAGE_PART_1 = "Click on this link to verify your account: <a href='http://localhost:8080/gum/controller?command=verification&userId=";

    String MAIL_MESSAGE_PART_2 = ">verification</a>";

    Client createClient(Client client) throws ServiceException;

    boolean editClient(Client client) throws ServiceException;

    boolean verification(int clientId) throws ServiceException;

    boolean refillMoney(int clientId, BigDecimal amount) throws ServiceException;

    boolean withdrawMoney(int clientId, BigDecimal amount) throws ServiceException;

    boolean assignDiscount(int clientId, BigDecimal discount) throws ServiceException;

    Client findClientById(int clientId) throws ServiceException;

    List<Client> findAllClient() throws ServiceException;// TODO: 9/29/2021

    List<Client> findAllActiveClient() throws ServiceException;// TODO: 9/29/2021

    List<Client> findAllClientByAnthroponym(String name, String surname) throws ServiceException;// TODO: 9/29/2021
}
