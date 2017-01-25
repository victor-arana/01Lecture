package courses.hibernate.dao;

public class JavaDBAccountDAOConstants {

	public static final String URL = "jdbc:derby://localhost:1527/LECTURE1";

	public static String CREATE_ACCOUNT = "INSERT INTO ACCOUNT(ACCOUNT_TYPE, CREATION_DATE, BALANCE) VALUES(?,CURRENT_TIMESTAMP,?)";

	public static String GET_ACCOUNT_ID = "SELECT IDENTITY_VAL_LOCAL() FROM ACCOUNT";


}
