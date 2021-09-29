package com.company.gum.service;

import com.company.gum.entity.User;
import com.company.gum.exception.ServiceException;

import java.util.List;

public interface UserService {

	User findUserById(int userId) throws ServiceException;

	User findUserByLogin(String login) throws ServiceException;

	User findUserByLoginAndPassword(String login, String password) throws ServiceException;

	boolean updateUserPassword(User user) throws ServiceException;

	boolean updateUserImage(User user) throws ServiceException;

	boolean deleteUser(int userId) throws ServiceException;

	boolean restoreUser(int userId) throws ServiceException;

	List<User> findAllUser() throws ServiceException;

	// FIXME: 9/7/2021
	boolean updateUserImage2(User user) throws ServiceException;
}
