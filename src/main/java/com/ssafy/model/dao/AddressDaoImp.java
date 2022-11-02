package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.dto.Address;
import com.ssafy.util.DBUtil;

public class AddressDaoImp implements AddressDao {
	private static AddressDao instance = new AddressDaoImp();
	private AddressDaoImp() {}
	public static AddressDao getInstance() {
		return instance;
	}
	private DBUtil dbutil = DBUtil.getInstance();
	

	@Override
	public Address Code2Name_Sido(String sidoCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();

			StringBuilder sql = new StringBuilder()
					.append("select *			\n")
					.append("from sidocode		\n")
					.append("where sidoCode=?	");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, sidoCode);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Address(
						rs.getString("sidoCode"),
						rs.getString("sidoName"), null, null, null, null
						);
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return null;
	}

	
	@Override
	public Address Code2Name_Gugun(String gugunCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();

			StringBuilder sql_builder = new StringBuilder()
					.append("select *			\n")
					.append("from guguncode		\n")
					.append("where gugunCode=?	");
			
			stmt = con.prepareStatement(sql_builder.toString());
			stmt.setString(1, gugunCode);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Address(
						null,
						null,
						rs.getString("gugunCode"),
						rs.getString("gugunName"), null, null
						);
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return null;
	}

	
	@Override
	public Address Code2Name_Dong(String dongCode) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();

			StringBuilder sql_builder = new StringBuilder()
					.append("select *			\n")
					.append("from dongcode		\n")
					.append("where dongCode=?	");
			
			stmt = con.prepareStatement(sql_builder.toString());
			stmt.setString(1, dongCode);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Address(
						null,
						rs.getString("sidoName"),
						null,
						rs.getString("gugunName"),
						rs.getString("dongCode"),
						rs.getString("dongName")
						);
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return null;
	}

	
	///////////////////////////////////////////////////////////////
	
	public Address Name2Code_Sido(String sidoName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();

			StringBuilder sql = new StringBuilder()
					.append("select *			\n")
					.append("from sidocode		\n")
					.append("where sidoName=?	");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, sidoName);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Address(
						rs.getString("sidoCode"),
						rs.getString("sidoName"), null, null, null, null
						);
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return null;
	}

	
	@Override
	public Address Name2Code_Gugun(String gugunName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();

			StringBuilder sql_builder = new StringBuilder()
					.append("select *			\n")
					.append("from guguncode		\n")
					.append("where gugunName=?	");
			
			stmt = con.prepareStatement(sql_builder.toString());
			stmt.setString(1, gugunName);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Address(
						null,
						null,
						rs.getString("gugunCode"),
						rs.getString("gugunName"), null, null
						);
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return null;
	}

	
	@Override
	public Address Name2Code_Dong(String dongName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();

			StringBuilder sql_builder = new StringBuilder()
					.append("select *			\n")
					.append("from dongcode		\n")
					.append("where dongName=?	");
			
			stmt = con.prepareStatement(sql_builder.toString());
			stmt.setString(1, dongName);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				return new Address(
						null,
						rs.getString("sidoName"),
						null,
						rs.getString("gugunName"),
						rs.getString("dongCode"),
						rs.getString("dongName")
						);
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return null;
	}
}
