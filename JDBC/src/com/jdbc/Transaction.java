package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Transaction {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");

		con.setAutoCommit(false);

		Statement stmt = con.createStatement();
		try {
			int i = stmt.executeUpdate("insert into employee values(13,'Laxman','prakash',25000,3,3)");
			i = stmt.executeUpdate("insert into employee values(12,'ram','prakash',25000,3,3)");

			con.commit();
		} catch (SQLException e) {
			
			con.rollback();
		}
		stmt.close();
		con.close();

	}

}
