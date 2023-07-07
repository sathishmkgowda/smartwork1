package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.ClassOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.repositories.CountryRepository;
import com.countryservice.demo.services.CountryService;



@SpringBootTest(classes= {ServiceMackitoTests.class})
public class ServiceMackitoTests 
{
	@Mock
	CountryRepository countryrep;
	
	@InjectMocks
	CountryService countryService;
	
	public List<Country> mycountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries()
	{
		List<Country> mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"india","Delhi"));
		mycountries.add(new Country(2,"USA","Washigton"));
		
		when(countryrep.findAll()).thenReturn(mycountries);   //mocking
	    assertEquals(2,countryService.getAllcountries().size());
	}
	
	@Test @Order(2)
	public void test_getCountrybyID()
	{
		List<Country> mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"india","Delhi"));
		mycountries.add(new Country(2,"USA","Washigton"));
		
		int CountryID=1;
		
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(CountryID,countryService.getCountrybyID(CountryID).getId());		
		
	}
	
	@Test @Order(3)
	public void test_getCountrybyName()
	{
		List<Country> mycountries=new ArrayList<Country>();
		mycountries.add(new Country(1,"india","Delhi"));
		mycountries.add(new Country(2,"USA","Washigton"));
		
		String Countryname="india";
		
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(Countryname,countryService.getCountrybyName(Countryname).getCountryName());		
	}
	@Test @Order(4)
	public void test_addCountry()
	{
		Country country=new Country(3,"Germany","berlin");
		
		when(countryrep.save(country)).thenReturn(country);
		assertEquals(country,countryService.addCountry(country));	
	}
	
	@Test @Order(5)
	public void test_updateCountry()
	{
		Country country=new Country(3,"italy","paris");
		
		when(countryrep.save(country)).thenReturn(country);
		assertEquals(country,countryService.updateCountry(country));	
	}
	
	@Test @Order(6)
	public void test_deleteCountry()
	{
		int country=3;
		countryService.deleteCountry(country);
		verify(countryrep,times(1)).deleteById(country);
	}
	

}
