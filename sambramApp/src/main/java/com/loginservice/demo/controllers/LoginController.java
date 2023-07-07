package com.loginservice.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginservice.demo.Services.LoginService;



@RestController
public class LoginController 
{
	@Autowired
	LoginService loginservice;
	
	@GetMapping("/login")
	public void login()
	{
		
	}
	

}
