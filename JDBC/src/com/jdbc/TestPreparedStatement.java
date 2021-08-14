package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestPreparedStatement {
	public static void main(String[] args) throws Exception {
		testAdd();
		//testUpdate();
		//testDelete();
		//testSelect();
		//testGet(1);
		
		
		

	}

	public static void testGet(int i) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn","root","root");
		int id=1;
		PreparedStatement ps=conn.prepareStatement("select * from employee where id=?");
		
		ps.setInt(1, id);
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			System.out.print(" "+rs.getString(1));
			System.out.print(" "+rs.getString(2));
			System.out.print(" "+rs.getString(3));
			System.out.println(" "+rs.getString(4));
		}
		
	}

	public static void testSelect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root","root");
		
		PreparedStatement ps= conn.prepareStatement("SELECT * FROM EMPLOYEE");
		ResultSet rs= ps.executeQuery();
		while (rs.next()) {
			System.out.print(" "+rs.getString(1));
			System.out.print(" "+rs.getString(2));
			System.out.print(" "+rs.getString(3));
			System.out.print(" "+rs.getString(4));
			System.out.println(" "+rs.getString(5));
			
		}
		
		
	}

	public static void testDelete() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/learn","root","root");
		
		int id= 17;
		 
		PreparedStatement ps= conn.prepareStatement("Delete from employee where id=?");
		 
		 ps.setInt(1,id);
		 ps.executeUpdate();
		 
		 System.out.println("DONE DELETION");
		
		
		
	}

	public static void testUpdate() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/learn","root","root");
		
		int id=16;
		String fname= "NEERAJ";
		String lname= "NARAYAN";
		int salary= 62000;
		int dept_id=5;
		int Dept_id=3;
		
		PreparedStatement ps = conn.prepareStatement("UPDATE employee SET FNAME=?, LNAME=? , SALARY=?, dept_id=?, Dept_id=? WHERE ID=? ");
				
		
		ps.setString(1, fname);
		ps.setString(2, lname);
		ps.setInt(3,salary);
		ps.setInt(4,dept_id);
		ps.setInt(5, Dept_id);
		ps.setInt(6,id);
		
		ps.executeUpdate();
		
		System.out.println("UPDATE DONE");
		
	
		
	}

	public static void testAdd() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/learn","root","root");
		
		conn.setAutoCommit(false);
		
		
		  int id=17;
		  String fname= "Ravi";
		  String lname= "Naresh";
		  int salary= 10000;
		  int dept_id=3;
		  int Dept_id=3;
		
		
		PreparedStatement ps = conn.prepareStatement(" INSERT INTO employee values (?,?,?,?,?,?)");
		
		ps.setInt(1,id);
		ps.setString(2, fname);
		ps.setString(3, lname);
		ps.setInt(4,salary);
		ps.setInt(5,dept_id);
		ps.setInt(6, Dept_id);
		
		ps.executeUpdate();
		
		conn.commit();
		System.out.println("Insertion Done");
		
	}


}
