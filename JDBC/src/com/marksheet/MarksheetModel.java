package com.marksheet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarksheetModel {

	public int Nextpk() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");
		int i = 0;
		PreparedStatement pd = conn.prepareStatement("select max(id) from marksheet");
		ResultSet rs = pd.executeQuery();
		while (rs.next()) {
			i = rs.getInt(1);

		}
		return i + 1;

	}

	public void testAdd(MarksheetBean bean) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");

		conn.setAutoCommit(false);

		PreparedStatement ps = conn.prepareStatement(" INSERT INTO marksheet values (?,?,?,?,?,?,?)");

		ps.setInt(1, Nextpk());
		ps.setString(2, bean.getRollno());
		ps.setString(3, bean.getFname());
		ps.setString(4, bean.getLname());
		ps.setInt(5, bean.getPhy());
		ps.setInt(6, bean.getChe());
		ps.setInt(7, bean.getMat());

		ps.executeUpdate();

		conn.commit();

	}

	public void testUpdate(MarksheetBean bean) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");

		conn.setAutoCommit(false);

		PreparedStatement ps = conn
				.prepareStatement("UPDATE marksheet SET ROLLNO=?, FNAME=?, LNAME=? , PHY=?, CHE=?, MAT=? WHERE ID=? ");

		ps.setString(1, bean.getRollno());
		ps.setString(2, bean.getFname());
		ps.setString(3, bean.getLname());
		ps.setInt(4, bean.getPhy());
		ps.setInt(5, bean.getChe());
		ps.setInt(6, bean.getMat());
		ps.setInt(7, bean.getId());

		ps.executeUpdate();
		conn.commit();

	}

	public static void testDelete(MarksheetBean bean) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");

		conn.setAutoCommit(false);

		PreparedStatement ps = conn.prepareStatement("Delete from marksheet where rollno=?");

		ps.setString(1, bean.getRollno());

		ps.executeUpdate();
		conn.commit();

	}

	public static void testGet(MarksheetBean bean) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");

		conn.setAutoCommit(false);

		PreparedStatement ps = conn.prepareStatement("select * from marksheet where Rollno=?");

		ps.setString(1, bean.getRollno());
		ps.executeQuery();
		/*
		 * ResultSet rs = ps.executeQuery(); while (rs.next()) {
		 * 
		 * System.out.print(" " + rs.getString(1)); System.out.print(" " +
		 * rs.getString(2)); System.out.print(" " + rs.getString(3));
		 * System.out.print(" " + rs.getString(4)); System.out.print(" " +
		 * rs.getString(5)); System.out.print(" " + rs.getString(6));
		 * System.out.println(" " + rs.getString(7)); }
		 */
		conn.commit();
	}

	public static void testSelect(MarksheetBean bean) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root", "root");

		conn.setAutoCommit(false);

		PreparedStatement ps = conn.prepareStatement("select * from marksheet");

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			System.out.print(" " + rs.getString(1));
			System.out.print(" " + rs.getString(2));
			System.out.print(" " + rs.getString(3));
			System.out.print(" " + rs.getString(4));
			System.out.print(" " + rs.getString(5));
			System.out.print(" " + rs.getString(6));
			System.out.println(" " + rs.getString(7));
		}
		conn.commit();
	}

	public static List<MarksheetBean> testMerit(MarksheetBean bean) throws Exception {
		/*
		 * Class.forName("com.mysql.jdbc.Driver"); Connection conn =
		 * DriverManager.getConnection("jdbc:mysql://localhost:3306/learn", "root",
		 * "root");
		 */
		ResourceBundle rb= ResourceBundle.getBundle("com.mearn.add");
		Class.forName(rb.getString("driver"));
		Connection conn=DriverManager.getConnection(rb.getString("url"),rb.getString("user"),rb.getString("pass"));
		conn.setAutoCommit(false);

		PreparedStatement ps = conn.prepareStatement(
				" select*,phy+che+mat as Total from marksheet where phy>40 and che>40 and mat>40 order by Total desc limit 0,5");
		
		
		ResultSet rs = ps.executeQuery();
		MarksheetBean bean1=null;
		List<MarksheetBean> list1 = new ArrayList<MarksheetBean>();
		while (rs.next()) {
			bean1 = new MarksheetBean();
			bean1.setId(rs.getInt(1));
			bean1.setRollno(rs.getString(2));
			bean1.setFname(rs.getString(3));
			bean1.setLname(rs.getString(4));
			bean1.setPhy(rs.getInt(5));
			bean1.setChe(rs.getInt(6));
			bean1.setMat(rs.getInt(7));
			list1.add(bean1);
			
		}
		conn.commit();
		return list1;
	}
}
