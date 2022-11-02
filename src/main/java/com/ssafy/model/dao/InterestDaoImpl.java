package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.dto.Interest;
import com.ssafy.util.DBUtil;

public class InterestDaoImpl implements InterestDao {
	
	private static InterestDao instance = new InterestDaoImpl();
	private InterestDaoImpl() {}
	public static InterestDao getInstance() {
		return instance;
	}
	
	private DBUtil db = DBUtil.getInstance();

	@Override
	public void insert(Interest interest) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			
			String sql = "insert into "
					+ "interest(id, aptCode, aptName, sidoCode, sidoName, gugunCode, gugunName, dongCode, dongName, lat, lng) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			stmt = conn.prepareStatement(sql);
			
			int idx = 1;
			stmt.setString(idx++, interest.getId());
			stmt.setInt(idx++, interest.getAptCode());
			stmt.setString(idx++, interest.getAptName());
			stmt.setString(idx++, interest.getSidoCode());
			stmt.setString(idx++, interest.getSidoName());
			stmt.setString(idx++, interest.getGugunCode());
			stmt.setString(idx++, interest.getGugunName());
			stmt.setString(idx++, interest.getDongCode());
			stmt.setString(idx++, interest.getDongName());
			stmt.setString(idx++, interest.getLat());
			stmt.setString(idx++, interest.getLng());
			
			stmt.executeUpdate();

		} finally {
			db.close(stmt, conn);
		}

	}

	@Override
	public List<String> search(String id) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			String sql = "select distinct aptName from interest where id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			List<String> list = new ArrayList<>();
			
			while (rs.next()) {
				list.add(rs.getString("aptName"));
			}
			return list;
		} finally {
			db.close(rs, stmt, conn);
		}
	}
	
	@Override
	public Interest searchByapt(String aptname) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = db.getConnection();
			System.out.println("aptname......." + aptname);
			String sql = "select * from interest where aptName=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, aptname);
			rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Yes");
				return new Interest(
							rs.getString("id"),
							rs.getInt("aptCode"),
							rs.getString("aptName"),
							rs.getString("sidoCode"),
							rs.getString("sidoName"),
							rs.getString("gugunCode"),
							rs.getString("gugunName"),
							rs.getString("dongCode"),
							rs.getString("dongName"),
							rs.getString("lat"),
							rs.getString("lng"));
				}		
			} finally {
			db.close(rs, stmt, conn);
		}
		System.out.println("No");
		return null;
	}
	
	/*@Override
	public Interest search(Interest interest) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = db.getConnection();
			
			String sql = "select * from interest where id=? and dongCode=?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, interest.getId());
			stmt.setString(2, interest.getDongCode());
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Interest (
									rs.getString("id"),
									rs.getString("dongCode"));
			}

		} finally {
			db.close(rs, stmt, conn);
		}
		
		return null;
	}

	@Override
	public void delete(String id) throws SQLException {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = db.getConnection();
			
			String sql = "delete from interest where id=?";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, id);
			
			stmt.executeUpdate();

		} finally {
			db.close(stmt, conn);
		}

	} */

}
