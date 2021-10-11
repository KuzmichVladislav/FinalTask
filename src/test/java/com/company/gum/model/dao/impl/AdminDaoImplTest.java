package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Admin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * The Class AdminDaoImplTest.
 *
 * @author Vladislav Kuzmich
 */
class AdminDaoImplTest {

	/**
	 * The instance.
	 */
	@Mock
	AdminDaoImpl instance;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test of findAdminById method, of class AdminDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindAdminById() throws DaoException {
		when(instance.findAdminById(anyInt())).thenReturn(new Admin());
		Admin result = instance.findAdminById(10);
		Assertions.assertEquals(new Admin(), result);
	}

	/**
	 * Test of editAdmin method, of class AdminDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testEditAdmin() throws DaoException {
		when(instance.editAdmin(any())).thenReturn(true);
		boolean result = instance.editAdmin(new Admin());
		Assertions.assertTrue(result);
	}

	/**
	 * Test of deleteUser method, of class AdminDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testDeleteUser() throws DaoException {
		when(instance.deleteUser(anyInt())).thenReturn(true);
		boolean result = instance.deleteUser(10);
		Assertions.assertTrue(result);
	}

	/**
	 * Test of restoreUser method, of class AdminDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testRestoreUser() throws DaoException {
		when(instance.restoreUser(anyInt())).thenReturn(true);
		boolean result = instance.restoreUser(10);
		Assertions.assertTrue(result);
	}
}
