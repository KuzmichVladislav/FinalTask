package com.company.gum.service;

import com.company.gum.entity.Admin;
import com.company.gum.exception.ServiceException;

import java.util.List;

public interface AdminService {

    Admin findAdminById(int adminId) throws ServiceException;

    List<Admin> findAllAdmin() throws ServiceException;// TODO: 9/28/2021 не понадобится

    boolean editAdmin(Admin admin) throws ServiceException;

    boolean deleteUser(int userId) throws ServiceException;

    boolean restoreUser(int userId) throws ServiceException;
}
