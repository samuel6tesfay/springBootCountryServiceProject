package com.example.demo;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.beans.Country;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.services.CountryService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {ServiceMackitoTest.class})
public class ServiceMackitoTest {
	
	@Mock
	CountryRepository countryrep;
	
	@InjectMocks
	CountryService countryService;
	
	public List<Country> mycountries;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi" ));
		mycountries.add(new Country(2,"USA","washington"));
		
		when(countryrep.findAll()).thenReturn(mycountries); // Mocking		
		
		assertEquals(2,countryService.getAllCountries().size());
		
	}
	
	@Test @Order(2)
	public void test_getCountrybyId() 
	{
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi" ));
		mycountries.add(new Country(2,"USA","washington"));
		int countryId=1;
		
		when(countryrep.findAll()).thenReturn(mycountries); // Mocking	
		assertEquals(countryId,countryService.getCountrybyId(countryId).getId());
		
	}
	
	@Test @Order(3)
	public void test_getCountrybyName() 
	{
		List<Country> mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"India","Delhi" ));
		mycountries.add(new Country(2,"USA","washington"));
		String countryName="India";
		
		when(countryrep.findAll()).thenReturn(mycountries); // Mocking	
		assertEquals(countryName,countryService.getCountrybyName(countryName).getCountryName());
		
	}

	
	@Test @Order(4)
	public void test_addCountry() {
		
		Country country = new Country(3,"Germany","Berlin");
		
		countryService.addCountry(country);
		when(countryrep.save(country)).thenReturn(country); // Mocking	
		assertEquals(country,countryService.addCountry(country));

	}
	
	
	@Test @Order(5)
	public void test_updateCountry() {
		
		Country country = new Country(3,"Germany","Berlin");
		
		countryService.updateCountry(country);
		when(countryrep.save(country)).thenReturn(country); // Mocking	
		assertEquals(country,countryService.updateCountry(country));

	}
	
	@Test @Order(6)
	public void test_deleteCountry() {
		Country country = new Country(3,"Germany","Berlin");
		countryService.deleteCountry(country);
		
		verify(countryrep,times(1)).delete(country);
	}
	
	
	
}
 





































