package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.controllers.addResponse;
import com.countryservice.demo.services.CountryService;

@SpringBootTest(classes= {ControllerMackitoTests.class})
public class ControllerMackitoTests 
{
	@Mock
	CountryService countyService;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> mycountries;
	Country country;
	
	@Test
	@Order(1)
	public void test_getCountries() 
	{
		List<Country> mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"india","Delhi"));
		mycountries.add(new Country(2,"USA","Washigton"));
		
		when(countyService.getAllcountries()).thenReturn(mycountries);   //mocking
		ResponseEntity<List<Country>> res = countryController.getCountries();
		
		assertEquals(HttpStatus.OK,	res.getStatusCode());
		 assertEquals(2,res.getBody().size());
	}
	
	@Test @Order(2)
	public void test_getCountrybyID()
	{
		
		country=new Country(2,"india","Delhi");
		int CountryID=2;
		
		when(countyService.getCountrybyID(CountryID)).thenReturn(country);
		assertEquals(CountryID,countyService.getCountrybyID(CountryID).getId());
		ResponseEntity<Country> res = countryController.getCountrybyID(CountryID);
		
		assertEquals(HttpStatus.OK,	res.getStatusCode());
		assertEquals(CountryID,countyService.getCountrybyID(CountryID).getId());
		
	}
	
	@Test @Order(3)
	public void test_getCountrybyName()
	{
		country=new Country(2,"india","Delhi");
		String Countryname="india";
		
		when(countyService.getCountrybyName(Countryname)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountrybyNAME(Countryname);
		
		assertEquals(HttpStatus.OK,	res.getStatusCode());
		assertEquals(Countryname,res.getBody().getCountryName());	
	}
	
	@Test @Order(4)
	public void test_addCountry()
	{
		Country country=new Country(3,"Germany","berlin");
		
		when(countyService.addCountry(country)).thenReturn(country);
		Country res = countryController.addCountry(country);
	
	}
	
	@Test @Order(5)
	public void test_updateCountry()
	{
		Country country=new Country(3,"italy","paris");
		int countryID=3;
		
		
		when(countyService.getCountrybyID(countryID)).thenReturn(country);
		when(countyService.updateCountry(country)).thenReturn(country);
		
		ResponseEntity<Country> res = countryController.updateCountry(countryID, country);
		assertEquals(HttpStatus.OK,	res.getStatusCode());
		assertEquals(country,res.getBody());	
	}
	
	@Test @Order(6)
	public void test_deleteCountry()
	{
		Country country=new Country(3,"italy","paris");
		int countryID=3;
		
		when(countyService.getCountrybyID(countryID)).thenReturn(country);
		addResponse res = countryController.deleteCountry(countryID);
		
	}
	
	
	
	
	
	
	

}
