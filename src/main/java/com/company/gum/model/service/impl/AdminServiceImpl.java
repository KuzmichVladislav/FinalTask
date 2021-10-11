package com.company.gum.model.service.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.model.dao.AdminDao;
import com.company.gum.model.dao.impl.AdminDaoImpl;
import com.company.gum.model.entity.Admin;
import com.company.gum.model.service.AdminService;

/**
 * The Class AdminServiceImpl.
 *
 * @author Vladislav Kuzmich
 */
public class AdminServiceImpl implements AdminService {

	/**
	 * The instance.
	 */
	private static AdminServiceImpl instance;

	/**
	 * The admin dao.
	 */
	private final AdminDao adminDao = AdminDaoImpl.getInstance();

	/**
	 * Instantiates a new admin service impl.
	 */
	private AdminServiceImpl() {
	}

	/**
	 * Gets the single instance of AdminServiceImpl.
	 *
	 * @return single instance of AdminServiceImpl
	 */
	public static AdminServiceImpl getInstance() {
		if (instance == null) {
			instance = new AdminServiceImpl();
		}
		return instance;
	}

	/**
	 * Find admin by id.
	 *
	 * @param adminId the admin id
	 * @return the admin
	 * @throws ServiceException the service exception
	 */
	@Override
	public Admin findAdminById(int adminId) throws ServiceException {
		Admin admin;
		try {
			admin = adminDao.findAdminById(adminId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return admin;
	}

	/**
	 * Edits the admin.
	 *
	 * @param admin the admin
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean editAdmin(Admin admin) throws ServiceException {
		boolean isUpdated;
		try {
			isUpdated = adminDao.editAdmin(admin);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isUpdated;
	}

	/**
	 * Delete user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean deleteUser(int userId) throws ServiceException {
		boolean isDeleted;
		try {
			isDeleted = adminDao.deleteUser(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isDeleted;
	}

	/**
	 * Restore user.
	 *
	 * @param userId the user id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean restoreUser(int userId) throws ServiceException {
		boolean isRestored;
		try {
			isRestored = adminDao.restoreUser(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return isRestored;
	}
}
