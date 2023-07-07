package com.countryservice.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.addResponse;
import com.countryservice.demo.repositories.CountryRepository;

@Component
@Service
public class CountryService 
{
	@Autowired
	CountryRepository countryrep;
	
	
	
	//below method is using hashmap storing the data
	/*static HashMap<Integer, Country> countryIdMap;

	//constructor
	public CountryService() 
	{
		//using hashmap to store the data instead of database
		  countryIdMap=new HashMap<Integer,Country>();
		  
		  Country indiaCountry = new Country(1,"india","delhi");
		  Country usaCountry = new Country(2,"USA","WASHIGTON");
		  Country ukCountry = new Country(3,"UK","London");
		  
		  countryIdMap.put(1,indiaCountry);
		  countryIdMap.put(2,usaCountry);
		  countryIdMap.put(3,ukCountry);	  
		  
	}
	*/
	
	
	public List<Country> getAllcountries()
	{
		
		return countryrep.findAll();
		
		
		
		
		
		//hasmap implementaion
		/*
		 * List countries = new ArrayList<>(countryIdMap.values()); //hashmapvalues
		 * added into the collections return countries;
		 */
	}
	
	public Country getCountrybyID(int id)
	{
		return countryrep.findById(id).get();
		
		
		
		//hashmap implementation
		/*
		 * Country country = countryIdMap.get(id); return country;
		 */
		
	}
	
	public Country getCountrybyName(String countryName)
	{
		
		List<Country> countries = countryrep.findAll();
		Country country=null;
		for (Country cou : countries) 
		{
			if(cou.getCountryName().equalsIgnoreCase(countryName))
			{
				country=cou;
			}
			
		}
		return country; 
			 
			 	
		//hashmap implementation
		/*
		 * Country country=null; for (int i : countryIdMap.keySet()) { if
		 * (countryIdMap.get(i).getCountryName().equals(countryName)) {
		 * country=countryIdMap.get(i); }
		 * 
		 * } return country;
		 */
	}
	
	public Country addCountry(Country country)
	{
		country.setId(getMaxId());
		countryrep.save(country);
		return country;
		
		//hashmap implementation
		/*
		 * country.setId(getMaxId()); countryIdMap.put(country.getId(),country); return
		 * country;
		 */
	}
	
	
	//utility method to get max id  
	public  int getMaxId() 
	{	
		return countryrep.findAll().size()+1;
		
		//hashmap implementation //static
		/*
		 * int max=0; for (int id : countryIdMap.keySet()) { if (max<=id) { max=id; } }
		 * return max+1;
		 */
	}
	
	public Country updateCountry(Country country)
	{
		countryrep.save(country);
		return country;
		
		
		//hashmap implementation
		/*
		 * if(country.getId()>0) { countryIdMap.put(country.getId(),country); } return
		 * country;
		 */
	}
	
	public addResponse deleteCountry(int id)
	{
		countryrep.deleteById(id);
		addResponse res=new addResponse();
		 res.setMsg("country deleted....."); 
		 res.setId(id); 
		 return res;
		
		
		//hashmap implementation
		/*
		 * countryIdMap.remove(id); addResponse res=new addResponse();
		 * res.setMsg("country deleted....."); res.setId(id); return res;
		 */
		
	}
	
}
