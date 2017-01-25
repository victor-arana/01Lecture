package courses.hibernate.service;

import courses.hibernate.dao.AccountDAOInterface;
import courses.hibernate.dao.AccountDAOOracle;
import courses.hibernate.vo.Account;

/**
 * Service layer for Account
 */
public class AccountService {
	
	AccountDAOInterface accountDAO;
	
	/**
	 * Get an instance of the AccountService
	 * @param databaseType type of the database
	 */
	public AccountService(){
		accountDAO = new AccountDAOOracle();
	}

	/**
	 * Create a new account
	 * 
	 * @param account
	 *            account to be created
	 * @return created account
	 */
	public Account createAccount(Account account) {
		return accountDAO.createAccount(account);
	}

	/**
	 * Update an account
	 * 
	 * @param account
	 *            account to be created
	 */
	public void updateAccount(Account account) {
		accountDAO.updateAccount(account);
	}

	/**
	 * Retrieve an account
	 * 
	 * @param accountId
	 *            identifier of the account to be retrieved
	 * @return account represented by the identifier provided
	 */
	public Account getAccount(long accountId) {
		return accountDAO.getAccount(accountId);
	}

	/**
	 * Delete account
	 * 
	 * @param account
	 *            account to be deleted
	 */
	public void deleteAccount(Account account) {
		accountDAO.deleteAccount(account);
	}
}
