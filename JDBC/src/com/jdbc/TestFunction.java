package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class TestFunction {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn","root","root");
		CallableStatement cs= conn.prepareCall("{?=CALL userCounts}");
	    cs.registerOutParameter(1, Types.INTEGER);
	    cs.execute();
	    int count= cs.getInt(1);
	    System.out.println(count);
	}


}
