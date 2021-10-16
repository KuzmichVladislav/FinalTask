package com.company.gum.model.dao.impl;

import com.company.gum.exception.DaoException;
import com.company.gum.model.entity.Trainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * The Class TrainerDaoImplTest.
 *
 * @author Vladislav Kuzmich
 */
class TrainerDaoImplTest {

	/**
	 * The instance.
	 */
	@Mock
	TrainerDaoImpl instance;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Test of createTrainer method, of class TrainerDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testCreateTrainer() throws DaoException {
		when(instance.createTrainer(any())).thenReturn(new Trainer());
		Trainer result = instance.createTrainer(new Trainer());
		Assertions.assertEquals(new Trainer(), result);
	}

	/**
	 * Test edit description.
	 * <p>
	 * Test of editDescription method, of class TrainerDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testEditDescription() throws DaoException {
		when(instance.editDescription(anyInt(), anyString())).thenReturn(true);
		boolean result = instance.editDescription(10, "test");
		Assertions.assertTrue(result);
	}

	/**
	 * Test of editExperience method, of class TrainerDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testEditExperience() throws DaoException {
		when(instance.editExperience(anyInt(), anyString())).thenReturn(true);
		boolean result = instance.editExperience(10, "test");
		Assertions.assertTrue(result);
	}

	/**
	 * Test of editTrainer method, of class TrainerDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testEditTrainer() throws DaoException {
		when(instance.editTrainer(any())).thenReturn(true);
		boolean result = instance.editTrainer(new Trainer());
		Assertions.assertTrue(result);
	}

	/**
	 * Test of findTrainerById method, of class TrainerDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindTrainerById() throws DaoException {
		when(instance.findTrainerById(anyInt())).thenReturn(new Trainer());
		Trainer result = instance.findTrainerById(10);
		Assertions.assertEquals(new Trainer(), result);
	}

	/**
	 * Test of findAllTrainer method, of class TrainerDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindAllTrainer() throws DaoException {
		when(instance.findAllTrainer()).thenReturn(Collections.singletonList(new Trainer()));
		List<Trainer> result = instance.findAllTrainer();
		Assertions.assertEquals(Collections.singletonList(new Trainer()), result);
	}

	/**
	 * Test of findAllActiveTrainer method, of class TrainerDaoImpl.
	 *
	 * @throws DaoException the dao exception
	 */
	@Test
	void testFindAllActiveTrainer() throws DaoException {
		when(instance.findAllActiveTrainer()).thenReturn(Collections.singletonList(new Trainer()));
		List<Trainer> result = instance.findAllActiveTrainer();
		Assertions.assertEquals(Collections.singletonList(new Trainer()), result);
	}
}