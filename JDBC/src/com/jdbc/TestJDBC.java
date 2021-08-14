package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");
		Statement stmt= con.createStatement();
		ResultSet rs= stmt.executeQuery("SELECT*FROM employee");
		
		while (rs.next()) {
			System.out.print("\t"+rs.getString("id"));
			System.out.print("\t"+rs.getString("fname"));
			System.out.print("\t"+rs.getString("lname"));
			System.out.println("\t"+rs.getString("salary"));
			
		}
		stmt.close();
		con.close();
	}

}
