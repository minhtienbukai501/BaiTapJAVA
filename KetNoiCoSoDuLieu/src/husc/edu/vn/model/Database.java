package husc.edu.vn.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Database {
	private  static final String DB_LOGIN = "sa";
	private  static final String DB_PASS = "123456";
	private static final String DB_NAME = "BaiTap"; // sau này chỉ cần copy code này dùng lại thôi  sửa lại một chút
	
	public static Connection getConnect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String DB_URL = "jdbc:sqlserver://MINHTIEN:1433; databaseName= "+ DB_NAME + 
					"; encrypt = true ; trustServerCertificate = true";
			try {
				return DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASS);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return  null;
	}
			
}
