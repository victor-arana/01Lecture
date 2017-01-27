package courses.hibernate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import courses.hibernate.vo.Account;

public class AccountDAOOracle implements AccountDAOInterface {
	
	private Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Connection connection = DriverManager.getConnection(OracleAccountDAOConstants.URL);
		connection.setAutoCommit(false);
		return connection;
	}
	
	/**
	 * Clean up database resources 
	 * @param connection connection to close
	 * @param statement statement to close
	 * @param resultSet resultSet to close
	 */
	protected void cleanupDatabaseResources(Connection connection,	Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a new account
	 * 
	 * @param account account to be created
	 * @return created account
	 */
	@Override
	public Account createAccount(Account account) {
		Connection connection = null;
		PreparedStatement getAccountIdStatement = null;
		PreparedStatement createAccountStatement = null;
		ResultSet resultSet = null;
		long accountId=0;
		try {
			connection = getConnection();
			getAccountIdStatement = connection.prepareStatement(OracleAccountDAOConstants.GET_ACCOUNT_ID);
			resultSet = getAccountIdStatement.executeQuery();
			resultSet.next();
			accountId = resultSet.getLong(1);

			createAccountStatement = connection.prepareStatement(OracleAccountDAOConstants.CREATE_ACCOUNT);
			createAccountStatement.setLong(1, accountId);
			createAccountStatement.setString(2, account.getAccountType());
			createAccountStatement.setDouble(3, account.getBalance());
			createAccountStatement.execute();

			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(null, getAccountIdStatement, resultSet);
			cleanupDatabaseResources(connection, createAccountStatement, null);
		}
		return getAccount(accountId);
	}

	/**
	 * Update an account
	 * 
	 * @param account
	 *            account to be created
	 */
	@Override
	public void updateAccount(Account account) {
		Connection connection = null;
		PreparedStatement updateAccountStatement = null;
		try {
			connection = getConnection();
			updateAccountStatement = connection.prepareStatement(AccountDAOConstants.UPDATE_ACCOUNT);
			updateAccountStatement.setDouble(1, account.getBalance());
			updateAccountStatement.setLong(2, account.getAccountId());
			updateAccountStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				e.printStackTrace();
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(connection, updateAccountStatement, null);
		}

	}
	
	/**
	 * Delete account from data store
	 * 
	 * @param account account to be deleted
	 */
	@Override
	public void deleteAccount(Account account) {
		Connection connection = null;
		PreparedStatement deleteAccountStatement = null;
		try {
			connection = getConnection();
			deleteAccountStatement = connection.prepareStatement(AccountDAOConstants.DELETE_ACCOUNT);
			deleteAccountStatement.setLong(1, account.getAccountId());
			deleteAccountStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(connection, deleteAccountStatement, null);
		}
	}

	/**
	 * Retrieve an account from the data store
	 * 
	 * @param accountId identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	@Override
	public Account getAccount(long accountId) {
		Connection connection = null;
		PreparedStatement getAccountStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			getAccountStatement = connection.prepareStatement(AccountDAOConstants.GET_ACCOUNT);
			getAccountStatement.setLong(1, accountId);
			resultSet = getAccountStatement.executeQuery();

			Account account = null;
			if (resultSet.next()) {
				account = new Account();
				account.setAccountId(resultSet.getLong("ACCOUNT_ID"));
				account.setAccountType(resultSet.getString("ACCOUNT_TYPE"));
				account.setBalance(resultSet.getDouble("BALANCE"));
				account.setCreationDate(resultSet.getDate("CREATION_DATE"));
			}
			connection.commit();
			return account;

		} catch (SQLException e) {
			e.printStackTrace();
			try{
				connection.rollback();
			}catch(SQLException e1){
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			cleanupDatabaseResources(connection, getAccountStatement, resultSet);
		}	
	}
}
