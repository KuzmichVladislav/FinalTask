package com.company.gum.model.pool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The Class ConnectionPoolTest.
 *
 * @author Vladislav Kuzmich
 */
class ConnectionPoolTest {

	/**
	 * Connection pool test.
	 */
	@Test
	void connectionPoolTest() {
		ConnectionPool instance = ConnectionPool.getInstance();
		Assertions.assertEquals(4, instance.size());
	}
}