package com.company.gum.service;

import com.company.gum.entity.Admin;
import com.company.gum.exception.ServiceException;

import java.util.List;

public interface AdminService {

    Admin findAdminById(int adminId) throws ServiceException;

    List<Admin> findAllAdmin() throws ServiceException;
}
