package com.ssafy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.dto.Address;
import com.ssafy.dto.House;
import com.ssafy.util.DBUtil;

public class HouseDaoImp implements HouseDao {
	private static HouseDao instance = new HouseDaoImp();
	private HouseDaoImp() {}
	public static HouseDao getInstance() {
		return instance;
	}
	private DBUtil dbutil = DBUtil.getInstance();
	
	@Override
	public House search(int no) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select *          \n");
			sql.append(" from housedeal    \n");
			sql.append(" where no=?    \n");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			if(rs.next()) {
				return new House(
						rs.getInt("no"),
						rs.getInt("aptCode"),
						rs.getString("aptName"),
						
						rs.getString("dealAmount"),
						rs.getInt("dealYear"),
						rs.getInt("dealMonth"),
						rs.getInt("dealDay"),
						
						rs.getString("area"),
						rs.getString("floor"),
						rs.getString("type"),
						rs.getString("rentMoney"),
						
						rs.getString("sidoName"),
						rs.getString("gugunName"),
						rs.getString("dongName"),
						rs.getString("jibun"),
						
						rs.getInt("buildYear"),
						rs.getString("lat"),
						rs.getString("lng"),
						rs.getString("img")
						);
			}
		} finally {
			dbutil.close(stmt, con);
		}
		return null;
	}

	
	@Override
	public List<House> searchAll() throws SQLException {
		Connection con = null;
		PreparedStatement stmt  = null;
		ResultSet rs = null;
		List<House> houses = new ArrayList<House>();
		try {
			con = dbutil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT *	\n");
			sql.append(" FROM housedeal left join (SELECT aptCode, aptName, sidoName, gugunName, ");
			sql.append(" houseinfo.dongName, houseinfo.jibun, houseinfo.buildYear, houseinfo.lat, houseinfo.lng, img	\n");
			sql.append("	FROM houseinfo join baseaddress	\n");
			sql.append("	using (dongCode)) info	\n");
			sql.append("using (aptCode)	");
			
			stmt = con.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			while(rs.next()) {
				houses.add(new House(
						rs.getInt("no"),
						rs.getInt("aptCode"),
						rs.getString("aptName"),
						
						rs.getString("dealAmount"),
						rs.getInt("dealYear"),
						rs.getInt("dealMonth"),
						rs.getInt("dealDay"),
						
						rs.getString("area"),
						rs.getString("floor"),
						rs.getString("type"),
						rs.getString("rentMoney"),
						
						rs.getString("sidoName"),
						rs.getString("gugunName"),
						rs.getString("dongName"),
						rs.getString("jibun"),
						
						rs.getInt("buildYear"),
						rs.getString("lat"),
						rs.getString("lng"),
						rs.getString("img")));
			}
			return houses;
		} finally {
			dbutil.close(rs, stmt, con);
		}
	}

	@Override
	public List<com.ssafy.dto.House> searchApt(String aptName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt  = null;
		ResultSet rs = null;		
		List<House> houses = new ArrayList<>();
		try {
			con = dbutil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT *	\n");
			sql.append("FROM housedeal join (SELECT aptCode, aptName, sidoName, gugunName, ");
			sql.append("	houseinfo.dongName, houseinfo.jibun, houseinfo.lat, houseinfo.buildYear, houseinfo.lng, img	\n");
			sql.append("	FROM houseinfo join baseaddress	\n");
			sql.append("	using (dongCode)	\n");
			sql.append("    WHERE aptName LIKE ?) info	\n");
			sql.append("using (aptCode)	\n");
			sql.append("order by no desc");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, "%"+aptName+"%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				houses.add(new House(
						rs.getInt("no"),
						rs.getInt("aptCode"),
						rs.getString("aptName"),
						
						rs.getString("dealAmount"),
						rs.getInt("dealYear"),
						rs.getInt("dealMonth"),
						rs.getInt("dealDay"),
						
						rs.getString("area"),
						rs.getString("floor"),
						rs.getString("type"),
						rs.getString("rentMoney"),
						
						rs.getString("sidoName"),
						rs.getString("gugunName"),
						rs.getString("dongName"),
						rs.getString("jibun"),
						
						rs.getInt("buildYear"),
						rs.getString("lat"),
						rs.getString("lng"),
						rs.getString("img")));
			}
			return houses;
		} finally {
			dbutil.close(rs, stmt, con);
		}
	}

	@Override
	public List<com.ssafy.dto.House> searchDong(String sidoName, String gugunName, String dongName)
			throws SQLException {
		Connection con = null;
		PreparedStatement stmt  = null;
		ResultSet rs = null;		
		List<House> houses = new ArrayList<>();
		if (!sidoName.equals("서울특별시")) {
			return houses;
		}
		
		try {
			con = dbutil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" select *                  \n");
			sql.append(" from housedeal join       \n");
			sql.append(" (SELECT aptCode, aptName, sidoName, gugunName, houseinfo.dongName, \n");
			sql.append(" 			houseinfo.jibun, houseinfo.buildYear, houseinfo.lat, houseinfo.lng, img\n");
			sql.append("			FROM houseinfo join baseaddress	\n");
			sql.append("			using (dongCode)	\n");
			sql.append("			where sidoName LIKE ? and gugunName LIKE ? and houseinfo.dongName LIKE ?)\n");
			sql.append(" info USING (aptCode)      \n");
			sql.append(" WHERE 1 ");
			
			sidoName = "%"+sidoName+"%";
			gugunName = "%"+gugunName+"%";
			dongName = "%"+dongName+"%";
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, sidoName);
			stmt.setString(2, gugunName);
			stmt.setString(3, dongName);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				houses.add(new House(
						rs.getInt("no"),
						rs.getInt("aptCode"),
						rs.getString("aptName"),
						
						rs.getString("dealAmount"),
						rs.getInt("dealYear"),
						rs.getInt("dealMonth"),
						rs.getInt("dealDay"),
						
						rs.getString("area"),
						rs.getString("floor"),
						rs.getString("type"),
						rs.getString("rentMoney"),
						
						rs.getString("sidoName"),
						rs.getString("gugunName"),
						rs.getString("dongName"),
						rs.getString("jibun"),
						
						rs.getInt("buildYear"),
						rs.getString("lat"),
						rs.getString("lng"),
						rs.getString("img")));
			}
			return houses;
		} finally {
			dbutil.close(rs, stmt, con);
		}
	}
	
	@Override
	public String aptName2DongCode(String aptName) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = dbutil.getConnection();

			StringBuilder sql = new StringBuilder()
					.append("select dongCode			\n")
					.append("from houseinfo		\n")
					.append("where aptName=?	");
			
			stmt = con.prepareStatement(sql.toString());
			stmt.setString(1, aptName);
		
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("dongCode");
			}
		} finally {
			dbutil.close(rs, stmt, con);
		}
		return null;
	}
	
	/*
	@Override
	public void insert(House house) throws SQLException {
		Connection con = null;
		PreparedStatement stmt  = null;
		try {
			con = dbutil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO housedeal(   \n");
			sql.append(" no, aptCode, dealAmount, dealYear, dealMonth, dealDay,   \n");
			sql.append(" area, floor, type, rentMoney)   \n");
			sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			stmt = con.prepareStatement(sql.toString());
			int idx = 1;
			stmt.setInt(idx++, house.getNo());
			stmt.setInt(idx++, house.getAptCode());
			stmt.setString(idx++, house.getDealAmount());
			stmt.setInt(idx++, house.getDealYear());
			stmt.setInt(idx++, house.getDealMonth());
			stmt.setInt(idx++, house.getDealDay());
			stmt.setString(idx++, house.getArea());
			stmt.setString(idx++, house.getFloor());
			stmt.setString(idx++, house.getType());
			stmt.setString(idx++, house.getRentMoney());
			
			stmt.executeUpdate();
			System.out.println("Done...........");
		} finally {
			dbutil.close(stmt, con);
		}
	}
	*/
	/*
	@Override
	public void update(House house) throws SQLException {
		Connection con = null;
		PreparedStatement stmt  = null;
		try {
			con = dbutil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(" UPDATE house set \n");
			sql.append(" no=?, aptCode=?, dealAmount=?, dealYear=?, dealMonth=?, dealDay=?,      \n");
			sql.append(" area=?, floor=?, type=?, rentMoney=?)      \n");
			stmt = con.prepareStatement(sql.toString());
			
			int idx = 1;
			stmt.setInt(idx++, house.getNo());
			stmt.setInt(idx++, house.getAptCode());
			stmt.setString(idx++, house.getDealAmount());
			stmt.setInt(idx++, house.getDealYear());
			stmt.setInt(idx++, house.getDealMonth());
			stmt.setInt(idx++, house.getDealDay());
			stmt.setString(idx++, house.getArea());
			stmt.setString(idx++, house.getFloor());
			stmt.setString(idx++, house.getType());
			stmt.setString(idx++, house.getRentMoney());
			
			stmt.executeUpdate();
		} finally {
			dbutil.close(stmt, con);
		}
	}
	*/
	/*
	@Override
	public void remove(int no) throws SQLException {
		Connection con = null;
		PreparedStatement stmt  = null;
		try {
			con = dbutil.getConnection();
			String sql="delete from housedeal where  code =?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} finally {
			dbutil.close(stmt, con);
		}
	}
	*/
}
