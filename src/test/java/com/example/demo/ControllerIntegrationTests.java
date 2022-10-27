package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.example.demo.beans.Country;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTests {
	
	
	@Test() 
	@Order(1)
	void getAllCountriesIntergrationTest() throws JSONException {
		
		String expected="[\n"
				+ "    {\n"
				+ "        \"id\": 2,\n"
				+ "        \"countryName\": \"USA\",\n"
				+ "        \"countryCapital\": \"Washington\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 3,\n"
				+ "        \"countryName\": \"UK\",\n"
				+ "        \"countryCapital\": \"Londan\"\n"
				+ "    }\n"
				+ "]";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries", String.class);
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test() 
	@Order(2)
	void getCountrieByIdIntergrationTest() throws JSONException {
		
		String expected="{\n"
				+ "    \"id\": 2,\n"
				+ "    \"countryName\": \"USA\",\n"
				+ "    \"countryCapital\": \"Washington\"\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/2", String.class);
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test() 
	@Order(3)
	void getCountrieByNameIntergrationTest() throws JSONException {
		
		String expected="{\n"
				+ "    \"id\": 2,\n"
				+ "    \"countryName\": \"USA\",\n"
				+ "    \"countryCapital\": \"Washington\"\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getcountries/countryname?name=USA", String.class);
		
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(4)
	void addCountryIntegrationTest() throws JSONException {
		
		Country country = new Country(4,"Germany","Berlin");
		
		String expected="{\n"
				+ "    \"id\": 3,\n"
				+ "    \"countryName\": \"Germany\",\n"
				+ "    \"countryCapital\": \"Berlin\"\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/addcountry", request,String.class);
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(5)
	void updateCountryIntegrationTest() throws JSONException {
		
		Country country = new Country(3,"Japan","Tokyo");
		
		String expected="{\n"
				+ "    \"id\": 3,\n"
				+ "    \"countryName\": \"Japan\",\n"
				+ "    \"countryCapital\": \"Tokyo\"\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/updatecountry/3",HttpMethod.PUT,request,String.class);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	@Test
	@Order(6)
	void deleteCountryIntegrationTest() throws JSONException {
		
		Country country = new Country(3,"Japan","Tokyo");
		
		String expected="{\n"
				+ "    \"id\": 3,\n"
				+ "    \"countryName\": \"Japan\",\n"
				+ "    \"countryCapital\": \"Tokyo\"\n"
				+ "}";
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Country> request = new HttpEntity<Country>(country,headers);
		
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/deletecountry/3",HttpMethod.DELETE,request,String.class);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
}












