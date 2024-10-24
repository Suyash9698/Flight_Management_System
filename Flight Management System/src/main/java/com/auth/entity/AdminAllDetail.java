package com.auth.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "admin_all_details")
public class AdminAllDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String registerName;
	private String dob;
	private String registerEmail;
	private String registerPassword;
	public AdminAllDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminAllDetail(int id, String registerName, String dob, String registerEmail, String registerPassword) {
		super();
		this.id = id;
		this.registerName = registerName;
		this.dob = dob;
		this.registerEmail = registerEmail;
		this.registerPassword = registerPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegisterName() {
		return registerName;
	}
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getRegisterEmail() {
		return registerEmail;
	}
	public void setRegisterEmail(String registerEmail) {
		this.registerEmail = registerEmail;
	}
	public String getRegisterPassword() {
		return registerPassword;
	}
	public void setRegisterPassword(String registerPassword) {
		this.registerPassword = registerPassword;
	}
	
	
	
	
	
	
}
