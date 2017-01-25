package courses.hibernate.dao;

import courses.hibernate.vo.Account;

public interface AccountDAOInterface {
	
	public Account createAccount(Account account);
	
	public void updateAccount(Account account);

	public void deleteAccount(Account account);
	
	public Account getAccount(long accountId);
}
