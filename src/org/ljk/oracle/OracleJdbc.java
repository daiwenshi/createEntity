package org.ljk.oracle;

import java.sql.*;

public class OracleJdbc {

	/*
	 * String dbUrl = "jdbc:oracle:thin:@192.168.56.101:1521:XE"; String theUser
	 * = "zhongchai"; String thePw = "zhongchai";
	 */
	public static String dbUrl = "jdbc:oracle:thin:@172.20.31.63:1521:tdcdb";
	public static String theUser = "szcidcca";
	public static String thePw = "szcidcca";
	Connection conn = null;
	Statement stmt;
	ResultSet rs = null;

	public OracleJdbc() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection(dbUrl, theUser, thePw);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean executeUpdate(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet executeQuery(String sql) {
		rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void close() {
		try {
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection(Connection con) {
		try {

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ResultSet rs;
		OracleJdbc conn = new OracleJdbc();
		rs = conn.executeQuery("select * from t_area t");
		try {
			while (rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}