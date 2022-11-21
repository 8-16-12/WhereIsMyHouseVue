package com.ssafy.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.StringTokenizer;

public class House implements Serializable, Comparator<House>{
	
	private int no;
	private int aptCode;
	private String aptName;
	
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	
	private String area;
	private String floor;
	private String type;
	private String rentMoney;
	
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String jibun;
	
	private int buildYear;
	private String lat;
	private String lng;
	private String img;
	
	public House() {}
	public House(int no, int aptCode, String aptName, 
				String dealAmount, int dealYear, int dealMonth, int dealDay,
				String area, String floor, String type, String rentMoney,
				String sidoName, String gugunName, String dongName, String jibun, 
				int buildYear, String lat, String lng, String img) {
		this.no = no;
		this.aptCode = aptCode;
		this.aptName = aptName;
		
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		
		this.area = area;
		this.floor = floor;
		this.type = type;
		this.rentMoney = rentMoney;
		
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.jibun = jibun;
		
		this.buildYear = buildYear;
		this.lat = lat;
		this.lng = lng;
		this.img = img;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	@Override
	public String toString() {
		return "House [no=" + no + ", aptCode=" + aptCode + ", aptName=" + aptName + ", dealAmount=" + dealAmount
				+ ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", area=" + area
				+ ", floor=" + floor + ", type=" + type + ", rentMoney=" + rentMoney + ", sidoName=" + sidoName
				+ ", gugunName=" + gugunName + ", dongName=" + dongName + ", jibun=" + jibun + ", buildYear="
				+ buildYear + ", lat=" + lat + ", lng=" + lng + ", img=" + img + "]";
	}
	@Override
	public int compare(House o1, House o2) {
		
		// o1 
		StringTokenizer st1 = new StringTokenizer((new StringTokenizer(o1.getDealAmount()," ")).nextToken(), ",");
		String line1 = "";
		
		while(st1.hasMoreTokens()) {
			line1 += st1.nextToken();
		}
		
		// o2
		StringTokenizer st2 = new StringTokenizer((new StringTokenizer(o2.getDealAmount()," ")).nextToken(), ",");
		String line2 = "";
		
		while(st2.hasMoreTokens()) {
			line2 += st2.nextToken();
		}
		
		return Integer.parseInt(line1) - Integer.parseInt(line2);
	}
	
	
}
