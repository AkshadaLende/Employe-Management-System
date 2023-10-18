package com.employment.management.entity;

import javax.persistence.Column;

public class RegisterRequest {
	private int id;
	
	private String name;
	
	private String address;

	private long adharnumber;

	private long mobileno;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getAdharnumber() {
		return adharnumber;
	}

	public void setAdharnumber(long adharnumber) {
		this.adharnumber = adharnumber;
	}

	public long getMobileno() {
		return mobileno;
	}

	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}
}
