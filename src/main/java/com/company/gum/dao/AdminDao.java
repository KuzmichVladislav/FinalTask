package com.company.gum.dao;

import com.company.gum.entity.Admin;
import com.company.gum.exception.DaoException;

import java.util.List;

public interface AdminDao {

    Admin findAdminById(int adminId) throws DaoException;

    List<Admin> findAllAdmin() throws DaoException;
}
