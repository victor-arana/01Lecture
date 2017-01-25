package courses.hibernate.dao;

public class AccountDAOConstants {

	public static String UPDATE_ACCOUNT = "UPDATE ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";

	public static String DELETE_ACCOUNT = "DELETE FROM ACCOUNT WHERE ACCOUNT_ID = ?";

	public static String GET_ACCOUNT = "SELECT ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE FROM ACCOUNT WHERE ACCOUNT_ID = ?";

}
