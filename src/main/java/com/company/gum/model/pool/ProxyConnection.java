package com.company.gum.model.pool;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * The Class ProxyConnection.
 *
 * @author Vladislav Kuzmich
 */
class ProxyConnection implements Connection {

	/**
	 * The connection.
	 */
	private final Connection connection;

	/**
	 * Instantiates a new proxy connection.
	 *
	 * @param connection the connection
	 */
	ProxyConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Really close.
	 *
	 * @throws SQLException the SQL exception
	 */
	void reallyClose() throws SQLException {
		connection.close();
	}

	/**
	 * Close.
	 */
	@Override
	public void close() {
		ConnectionPool.getInstance().releaseConnection(this);
	}

	/**
	 * Creates the statement.
	 *
	 * @return the statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Statement createStatement() throws SQLException {
		return connection.createStatement();
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql the sql
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql the sql
	 * @return the callable statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return connection.prepareCall(sql);
	}

	/**
	 * Native SQL.
	 *
	 * @param sql the sql
	 * @return the string
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String nativeSQL(String sql) throws SQLException {
		return connection.nativeSQL(sql);
	}

	/**
	 * Gets the auto commit.
	 *
	 * @return the auto commit
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean getAutoCommit() throws SQLException {
		return connection.getAutoCommit();
	}

	/**
	 * Sets the auto commit.
	 *
	 * @param autoCommit the new auto commit
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}

	/**
	 * Commit.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void commit() throws SQLException {
		connection.commit();
	}

	/**
	 * Rollback.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void rollback() throws SQLException {
		connection.rollback();
	}

	/**
	 * Checks if is closed.
	 *
	 * @return true, if is closed
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}

	/**
	 * Gets the meta data.
	 *
	 * @return the meta data
	 * @throws SQLException the SQL exception
	 */
	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return connection.getMetaData();
	}

	/**
	 * Checks if is read only.
	 *
	 * @return true, if is read only
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isReadOnly() throws SQLException {
		return connection.isReadOnly();
	}

	/**
	 * Sets the read only.
	 *
	 * @param readOnly the new read only
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		connection.setReadOnly(readOnly);
	}

	/**
	 * Gets the catalog.
	 *
	 * @return the catalog
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String getCatalog() throws SQLException {
		return connection.getCatalog();
	}

	/**
	 * Sets the catalog.
	 *
	 * @param catalog the new catalog
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setCatalog(String catalog) throws SQLException {
		connection.setCatalog(catalog);
	}

	/**
	 * Gets the transaction isolation.
	 *
	 * @return the transaction isolation
	 * @throws SQLException the SQL exception
	 */
	@Override
	public int getTransactionIsolation() throws SQLException {
		return connection.getTransactionIsolation();
	}

	/**
	 * Sets the transaction isolation.
	 *
	 * @param level the new transaction isolation
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		connection.setTransactionIsolation(level);
	}

	/**
	 * Gets the warnings.
	 *
	 * @return the warnings
	 * @throws SQLException the SQL exception
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		return connection.getWarnings();
	}

	/**
	 * Clear warnings.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void clearWarnings() throws SQLException {
		connection.clearWarnings();
	}

	/**
	 * Creates the statement.
	 *
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @return the statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return connection.createStatement(resultSetType, resultSetConcurrency);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @return the callable statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * Gets the type map.
	 *
	 * @return the type map
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return connection.getTypeMap();
	}

	/**
	 * Sets the type map.
	 *
	 * @param map the map
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		connection.setTypeMap(map);
	}

	/**
	 * Gets the holdability.
	 *
	 * @return the holdability
	 * @throws SQLException the SQL exception
	 */
	@Override
	public int getHoldability() throws SQLException {
		return connection.getHoldability();
	}

	/**
	 * Sets the holdability.
	 *
	 * @param holdability the new holdability
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setHoldability(int holdability) throws SQLException {
		connection.setHoldability(holdability);
	}

	/**
	 * Sets the savepoint.
	 *
	 * @return the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Savepoint setSavepoint() throws SQLException {
		return connection.setSavepoint();
	}

	/**
	 * Sets the savepoint.
	 *
	 * @param name the name
	 * @return the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return connection.setSavepoint(name);
	}

	/**
	 * Rollback.
	 *
	 * @param savepoint the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		connection.rollback(savepoint);
	}

	/**
	 * Release savepoint.
	 *
	 * @param savepoint the savepoint
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		connection.releaseSavepoint(savepoint);
	}

	/**
	 * Creates the statement.
	 *
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @param resultSetHoldability the result set holdability
	 * @return the statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @param resultSetHoldability the result set holdability
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
	                                          int resultSetHoldability) throws SQLException {
		return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare call.
	 *
	 * @param sql                  the sql
	 * @param resultSetType        the result set type
	 * @param resultSetConcurrency the result set concurrency
	 * @param resultSetHoldability the result set holdability
	 * @return the callable statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
	                                     int resultSetHoldability) throws SQLException {
		return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql               the sql
	 * @param autoGeneratedKeys the auto generated keys
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return connection.prepareStatement(sql, autoGeneratedKeys);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql           the sql
	 * @param columnIndexes the column indexes
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return connection.prepareStatement(sql, columnIndexes);
	}

	/**
	 * Prepare statement.
	 *
	 * @param sql         the sql
	 * @param columnNames the column names
	 * @return the prepared statement
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return connection.prepareStatement(sql, columnNames);
	}

	/**
	 * Creates the clob.
	 *
	 * @return the clob
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Clob createClob() throws SQLException {
		return connection.createClob();
	}

	/**
	 * Creates the blob.
	 *
	 * @return the blob
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Blob createBlob() throws SQLException {
		return connection.createBlob();
	}

	/**
	 * Creates the N clob.
	 *
	 * @return the n clob
	 * @throws SQLException the SQL exception
	 */
	@Override
	public NClob createNClob() throws SQLException {
		return connection.createNClob();
	}

	/**
	 * Creates the SQLXML.
	 *
	 * @return the sqlxml
	 * @throws SQLException the SQL exception
	 */
	@Override
	public SQLXML createSQLXML() throws SQLException {
		return connection.createSQLXML();
	}

	/**
	 * Checks if is valid.
	 *
	 * @param timeout the timeout
	 * @return true, if is valid
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isValid(int timeout) throws SQLException {
		return connection.isValid(timeout);
	}

	/**
	 * Sets the client info.
	 *
	 * @param name  the name
	 * @param value the value
	 * @throws SQLClientInfoException the SQL client info exception
	 */
	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		connection.setClientInfo(name, value);
	}

	/**
	 * Gets the client info.
	 *
	 * @param name the name
	 * @return the client info
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String getClientInfo(String name) throws SQLException {
		return connection.getClientInfo(name);
	}

	/**
	 * Gets the client info.
	 *
	 * @return the client info
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Properties getClientInfo() throws SQLException {
		return connection.getClientInfo();
	}

	/**
	 * Sets the client info.
	 *
	 * @param properties the new client info
	 * @throws SQLClientInfoException the SQL client info exception
	 */
	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		connection.setClientInfo(properties);
	}

	/**
	 * Creates the array of.
	 *
	 * @param typeName the type name
	 * @param elements the elements
	 * @return the array
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return connection.createArrayOf(typeName, elements);
	}

	/**
	 * Creates the struct.
	 *
	 * @param typeName   the type name
	 * @param attributes the attributes
	 * @return the struct
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return connection.createStruct(typeName, attributes);
	}

	/**
	 * Gets the schema.
	 *
	 * @return the schema
	 * @throws SQLException the SQL exception
	 */
	@Override
	public String getSchema() throws SQLException {
		return connection.getSchema();
	}

	/**
	 * Sets the schema.
	 *
	 * @param schema the new schema
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setSchema(String schema) throws SQLException {
		connection.setSchema(schema);
	}

	/**
	 * Abort.
	 *
	 * @param executor the executor
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void abort(Executor executor) throws SQLException {
		connection.abort(executor);
	}

	/**
	 * Sets the network timeout.
	 *
	 * @param executor     the executor
	 * @param milliseconds the milliseconds
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		connection.setNetworkTimeout(executor, milliseconds);
	}

	/**
	 * Gets the network timeout.
	 *
	 * @return the network timeout
	 * @throws SQLException the SQL exception
	 */
	@Override
	public int getNetworkTimeout() throws SQLException {
		return connection.getNetworkTimeout();
	}

	/**
	 * Begin request.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void beginRequest() throws SQLException {
		connection.beginRequest();
	}

	/**
	 * End request.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void endRequest() throws SQLException {
		connection.endRequest();
	}

	/**
	 * Sets the sharding key if valid.
	 *
	 * @param shardingKey      the sharding key
	 * @param superShardingKey the super sharding key
	 * @param timeout          the timeout
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean setShardingKeyIfValid(ShardingKey shardingKey, ShardingKey superShardingKey, int timeout)
			throws SQLException {
		return connection.setShardingKeyIfValid(shardingKey, superShardingKey, timeout);
	}

	/**
	 * Sets the sharding key if valid.
	 *
	 * @param shardingKey the sharding key
	 * @param timeout     the timeout
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean setShardingKeyIfValid(ShardingKey shardingKey, int timeout) throws SQLException {
		return connection.setShardingKeyIfValid(shardingKey, timeout);
	}

	/**
	 * Sets the sharding key.
	 *
	 * @param shardingKey      the sharding key
	 * @param superShardingKey the super sharding key
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setShardingKey(ShardingKey shardingKey, ShardingKey superShardingKey) throws SQLException {
		connection.setShardingKey(shardingKey, superShardingKey);
	}

	/**
	 * Sets the sharding key.
	 *
	 * @param shardingKey the new sharding key
	 * @throws SQLException the SQL exception
	 */
	@Override
	public void setShardingKey(ShardingKey shardingKey) throws SQLException {
		connection.setShardingKey(shardingKey);
	}

	/**
	 * Unwrap.
	 *
	 * @param <T>   the generic type
	 * @param iface the iface
	 * @return the t
	 * @throws SQLException the SQL exception
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return connection.unwrap(iface);
	}

	/**
	 * Checks if is wrapper for.
	 *
	 * @param iface the iface
	 * @return true, if is wrapper for
	 * @throws SQLException the SQL exception
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return connection.isWrapperFor(iface);
	}
}