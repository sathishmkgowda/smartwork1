package com.countryservice.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.countryservice.demo.beans.Country;

@SpringBootTest
public class ControllerIntegrationTests
{
	@Test
	@Order(1)
	void getallCountriesIntegrationTest() throws JSONException
	{
		String expected="Json Data ";
		
		
		TestRestTemplate restTemplate=new TestRestTemplate();
		 ResponseEntity<String>  response=restTemplate.getForEntity("http://localhost:8080/getcountries	",String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expected,response.getBody(), false);	
	}
	
	
	@Test
	@Order(2)
	void getallCountryByIdIntegrationTest() throws JSONException
	{
		String expected="Json Data ";
	
		TestRestTemplate restTemplate=new TestRestTemplate();
		 ResponseEntity<String>  response=restTemplate.getForEntity("http://localhost:8080/getcountries/1",String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expected,response.getBody(), false);
		
	}
	
	@Test
	@Order(3)
	void getallCountryByNameIntegrationTest() throws JSONException
	{
		String expected="Json Data ";
	
		TestRestTemplate restTemplate=new TestRestTemplate();
		 ResponseEntity<String>  response=restTemplate.getForEntity("http://localhost:8080/getcountries/countryname?name=india",String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		
		JSONAssert.assertEquals(expected,response.getBody(), false);
		
	}
	
	@Test
	@Order(4)
	void addCountryIntegrationTest() throws JSONException
	{
		Country country=new Country(3,"germany","berlin");
		String expected="Json Data ";
	
		TestRestTemplate restTemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		
		 ResponseEntity<String>  response=restTemplate.postForEntity("http://localhost:8080/addcountry",request,String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		
		JSONAssert.assertEquals(expected,response.getBody(), false);	
	}

	@Test
	@Order(5)
	void updateCountryIntegrationTest() throws JSONException
	{
		Country country=new Country(3,"japan","tokyo");
		String expected="Json Data ";
	
		TestRestTemplate restTemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		
		 ResponseEntity<String>  response=restTemplate.exchange("http://localhost:8080/updatecountry/3",HttpMethod.PUT,request,String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		JSONAssert.assertEquals(expected,response.getBody(), false);	
	}
	
	@Test
	@Order(6)
	void deleteCountryIntegrationTest() throws JSONException
	{
		Country country=new Country(3,"japan","tokyo");
		String expected="Json Data ";
	
		TestRestTemplate restTemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		
		 ResponseEntity<String>  response=restTemplate.exchange("http://localhost:8080/deletecountry/3",HttpMethod.DELETE,request,String.class); 
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		
		JSONAssert.assertEquals(expected,response.getBody(), false);	
		
		///restTemplate.delete("http://localhost:8080/deletecountry/3");  we can directly delete using this method but we cant do verification
	}
	
	
}
