package com.company.gum.model.dao;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Admin;

/**
 * The Interface AdminDao.
 *
 * @author Vladislav Kuzmich
 */
public interface AdminDao {

    /**
     * The image src prefix.
     */
    String IMAGE_SRC_PREFIX = "data:image/jpg;base64,";

    /**
     * The default image.
     */
    String DEFAULT_IMAGE = "https://i.ibb.co/mDTmCZn/png-transparent-computer-icons-user-profile-user-avatar-blue-heroes-electric-blue.png";

    /**
     * Find admin by id.
     *
     * @param adminId the admin id
     * @return the admin
     * @throws DaoException the dao exception
     */
    Admin findAdminById(int adminId) throws DaoException;

    /**
     * Edits the admin.
     *
     * @param admin the admin
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean editAdmin(Admin admin) throws DaoException;

    /**
     * Delete user.
     *
     * @param userId the user id
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean deleteUser(int userId) throws DaoException;

    /**
     * Restore user.
     *
     * @param userId the user id
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean restoreUser(int userId) throws DaoException;
}
