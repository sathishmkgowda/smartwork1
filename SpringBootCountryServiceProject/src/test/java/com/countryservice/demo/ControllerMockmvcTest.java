package com.countryservice.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.countryservice.demo.beans.Country;
import com.countryservice.demo.controllers.CountryController;
import com.countryservice.demo.services.CountryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@ComponentScan(basePackages = "com.countryservice.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {ControllerMockmvcTest.class})
public class ControllerMockmvcTest 
{
	 @Autowired
	  MockMvc mockMVC;
	 
	 @Mock
	 CountryService countyService;
	 
	 @InjectMocks
	 CountryController countryController;
	 
	 List<Country> mycountries;     //contains multiple country data
	 Country country;              //contains single country data
		
	 
	 @BeforeEach
	 public void setUp()
	 {
		 mockMVC=MockMvcBuilders.standaloneSetup(countryController).build();
	 }
	 
	 @Test
		@Order(1)
		public void test_getAllCountries() throws Exception 
		{
			List<Country> mycountries=new ArrayList<Country>();
			mycountries.add(new Country(1,"india","Delhi"));
			mycountries.add(new Country(2,"USA","Washigton"));
			
			when(countyService.getAllcountries()).thenReturn(mycountries);  //mackito to mock the service
			
			this.mockMVC.perform(get("/getcountries"))             //mockMVC{
			     .andExpect(status().isFound())
			     .andDo(print());
		}
	 
	 @Test @Order(2)
		public void test_getCountrybyID() throws Exception
		{
			
			country=new Country(2,"USA","Washigton");
			int CountryID=2;
			
			when(countyService.getCountrybyID(CountryID)).thenReturn(country);
			
			this.mockMVC.perform(get("/getcountries/{id}",CountryID))  
			.andExpect(status().isFound())
			.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Washigton"))
		     .andDo(print());
			
		}
	 
	 @Test @Order(3)
		public void test_getCountrybyNAME() throws Exception
		{
			
			country=new Country(2,"USA","Washigton");
			String Countryname="USA";
			
			when(countyService.getCountrybyName(Countryname)).thenReturn(country);
			
			this.mockMVC.perform(get("/getcountries/countryname").param("name","USA"))
			.andExpect(status().isFound())
			.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("USA"))
			.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Washigton"))
		     .andDo(print());
			
		}
	 
	 @Test @Order(4)
	 public void test_addCountry() throws Exception
	 {
		 Country country=new Country(3,"Germany","berlin");
			
			when(countyService.addCountry(country)).thenReturn(country);
			
			ObjectMapper mapper= new ObjectMapper();
			
			String jsonbody = mapper.writeValueAsString(country);
			
			this.mockMVC.perform(post("/addcountry")
					 .content(jsonbody)
					 .contentType(MediaType.APPLICATION_JSON))
			         .andExpect(status().isCreated())
			         .andDo(print());
	 }
	
	@Test
	@Order(5)
	public void test_updateCountry() throws Exception
	{
		Country country=new Country(3,"Japan","Tokyo");
		int countryID=3;
		
		when(countyService.getCountrybyID(countryID)).thenReturn(country);  //MOCKING
		when(countyService.updateCountry(country)).thenReturn(country);
		
		ObjectMapper mapper= new ObjectMapper();      //Converting request body to json body
		String jsonbody = mapper.writeValueAsString(country);
		
		this.mockMVC.perform(put("/updatecountry/{id}",countryID)
				 .content(jsonbody)
				 .contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Japan"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Tokyo"))
	     .andDo(print());
		
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() throws Exception
	{
		Country country=new Country(3,"Japan","Tokyo");
		int countryID=3;
		when(countyService.getCountrybyID(countryID)).thenReturn(country); //mocking
		
		this.mockMVC.perform(delete("/deletecountry/{id}",countryID))
		.andExpect(status().isOk());
		
	}
}
