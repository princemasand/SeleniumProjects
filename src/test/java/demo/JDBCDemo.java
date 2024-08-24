package demo;


import CommonLibs.Implementation.Base;
import CommonLibs.Implementation.JDBCConnection;

public class JDBCDemo extends Base{
	
	public static void main(String [] args) {
		JDBCConnection.connection();
		String queryString = "select * from statusmaster";
		JDBCConnection.prepareStatement(queryString);
		JDBCConnection.close();
	}

} 