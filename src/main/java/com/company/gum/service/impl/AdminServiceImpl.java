package com.company.gum.service.impl;

import com.company.gum.dao.AdminDao;
import com.company.gum.dao.impl.AdminDaoImpl;
import com.company.gum.entity.Admin;
import com.company.gum.exception.DaoException;
import com.company.gum.exception.ServiceException;
import com.company.gum.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private static final Logger logger = LogManager.getLogger();

    private static AdminService adminService = new AdminServiceImpl();

    private AdminDao adminDao = AdminDaoImpl.getInstance();

    private AdminServiceImpl() {
    }

    public static AdminService getInstance() {
        return adminService;
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
}
