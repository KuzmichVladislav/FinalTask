package com.company.gum.service.impl;

import com.company.gum.dao.AdminDao;
import com.company.gum.dao.impl.AdminDaoImpl;
import com.company.gum.entity.Admin;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private static AdminServiceImpl mInstance;

    private AdminDao adminDao = AdminDaoImpl.getInstance();

    private AdminServiceImpl() {
    }

    public static AdminServiceImpl getInstance() {
        if (mInstance == null) {
            mInstance = new AdminServiceImpl();
        }
        return mInstance;
    }

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

    @Override
    public List<Admin> findAllAdmin() throws ServiceException {
        List<Admin> admins;
        try {
            admins = adminDao.findAllAdmin();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return admins;
    }

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
}
