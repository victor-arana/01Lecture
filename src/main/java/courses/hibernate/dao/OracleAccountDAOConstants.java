package courses.hibernate.dao;

public class OracleAccountDAOConstants {

	public static final String URL = "jdbc:oracle:thin:lecture1/reverload@localhost:1521:XE";

	public static String GET_ACCOUNT_ID = "SELECT ACCOUNT_ID_SEQ.NEXTVAL FROM DUAL";

	public static String CREATE_ACCOUNT = "INSERT INTO ACCOUNT(ACCOUNT_ID, ACCOUNT_TYPE, CREATION_DATE, BALANCE) VALUES(?,?,SYSDATE,?)";

}
