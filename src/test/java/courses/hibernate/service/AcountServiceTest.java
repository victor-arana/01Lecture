package courses.hibernate.service;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import courses.hibernate.vo.Account;

public class AcountServiceTest {
	
	private AccountService getAccountService(){
		return new AccountService();
	}
	
	@Test
	public void testCreateAccount(){
		// create the account
		// ------ --- -------
		Account account = new Account();
		account.setAccountType(Account.ACCOUNT_TYPE_SAVINGS);
		account.setCreationDate(new Date());
		account.setBalance(1000L);
		
		Assert.assertTrue(account.getAccountId() == 0);
		
		// save the account
		// ---- --- -------
		AccountService accountService = getAccountService();
		account = accountService.createAccount(account);
		
		System.out.println("var account = " + account);
		
		// check that IDs were set after the hbm session
		// ----- ---- --- ---- --- ----- --- --- -------
		Assert.assertTrue(account.getAccountId() > 0);
		
		// cleanup
		// -------
		deleteAccount(account);
	}
	
	private void deleteAccount(Account account){
		AccountService accountService = getAccountService();
		accountService.deleteAccount(account);
	}
	

}
