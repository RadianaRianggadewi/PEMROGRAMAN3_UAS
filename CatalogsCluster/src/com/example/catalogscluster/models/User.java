package com.example.catalogscluster.models;

public class User {
	private String nama;
	private long ktp;
	private String alamat;
	private long hp;
	private String email;
	private String user;
	private String pass;
	
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public long getKtp() {
		return ktp;
	}
	
	public void setKtp(long ktp) {
		this.ktp = ktp;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public long getHP() {
		return hp;
	}
	
	public void setHP(long hp) {
		this.hp = hp;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}