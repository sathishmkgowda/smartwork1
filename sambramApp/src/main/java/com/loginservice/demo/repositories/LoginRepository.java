package com.loginservice.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginservice.demo.beans.Login;

public interface LoginRepository extends JpaRepository<Login, String>{

}
