package com.loginservice.demo.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="userdetails")
public class Login 
{
	@Column(name="user_name")
    String username;
	
	@Column(name="user_passw ")
	String password;
	
	
	
	 public Login (String username,String password)
	 {
		 this.username=username;
		 this.password=password;
	 }
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

  
}
