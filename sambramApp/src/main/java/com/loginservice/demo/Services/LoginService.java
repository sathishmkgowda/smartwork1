package com.loginservice.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.loginservice.demo.repositories.LoginRepository;

@Component
@Service
public class LoginService 
{
	@Autowired
	LoginRepository loginrep;
	
	public void loginverify()
	{
		
	}
	

}
