package com.sambram1.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService 
{
	
	public int loginValidation(String username,String password);


}
