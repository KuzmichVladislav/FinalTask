package com.company.gum.model.service;

import com.company.gum.exception.ServiceException;
import com.company.gum.model.entity.Admin;

/**
 * The Interface AdminService.
 *
 * @author Vladislav Kuzmich
 */
public interface AdminService {

	/**
	 * Find admin by id.
	 *
	 * @param adminId the admin id
	 * @return the admin
	 * @throws ServiceException the service exception
	 */
	Admin findAdminById(int adminId) throws ServiceException;

	/**
	 * Edits the admin.
	 *
	 * @param admin the admin
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean editAdmin(Admin admin) throws ServiceException;

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean deleteUser(int userId) throws ServiceException;

	/**
	 * Restore user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean restoreUser(int userId) throws ServiceException;
}
