package com.countryservice.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.services.CountryService;

@RestController
public class CountryController 
{
	@Autowired
	CountryService countyservice; 
	
	/*
	 * @GetMapping("/getcountries") public List<Country> getCountries() {
	 * 
	 * return countyservice.getAllcountries();
	 * 
	 * }
	 */
	
	@GetMapping("/getcountries") 
	public ResponseEntity<List<Country>> getCountries() 
	{
		try {
		List<Country> country = countyservice.getAllcountries();
		return new ResponseEntity<List<Country>>(country,HttpStatus.OK);
		}
		catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

//	@GetMapping("/getcountries/{id}")
//	public Country getCountrybyID(@PathVariable(value = "id") int id)
//	{
//		return countyservice.getCountrybyID(id);
//	}
	
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountrybyID(@PathVariable(value = "id") int id)
	{
		try {
		Country   Country=countyservice.getCountrybyID(id);
		return new ResponseEntity<Country>(Country,HttpStatus.OK);
		}
		catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
//	@GetMapping("/getcountries/countryname")
//	public Country getCountrybyNAME(@RequestParam(value = "name") String countryname)
//	{
//		 return countyservice.getCountrybyName(countryname);
//	}
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountrybyNAME(@RequestParam(value = "name") String countryname) 
	{  
		try {
		Country   Country=countyservice.getCountrybyName(countryname);
		return new ResponseEntity<Country>(Country,HttpStatus.OK);
		}
		catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country county)
	{
		return countyservice.addCountry(county);
		
	}
	

//	@PutMapping("/updatecountry")
//	public Country updateCountry(@RequestBody Country county)
//	{
//		return countyservice.updateCountry(county);
//		
//	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id,@RequestBody Country county)
	{     try {
	            Country existcountry = countyservice.getCountrybyID(id);
	            
	            existcountry.setCountryName(county.getCountryName());
	            existcountry.setCountryCapital(county.getCountryCapital());
	            
	            Country updated_country = countyservice.updateCountry(existcountry);
	            return new ResponseEntity<Country>(updated_country,HttpStatus.OK);
	}
	catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		
	}
	
	
	@DeleteMapping("/deletecountry/{id}")
	public addResponse deleteCountry(@PathVariable(value = "id") int id)
	{
		return countyservice.deleteCountry(id);
		
	}
	 
	
}
