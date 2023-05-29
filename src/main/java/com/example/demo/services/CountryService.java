package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.beans.Country;
import com.example.demo.controllers.AddResponse;
import com.example.demo.repositories.CountryRepository;

@Component
@Service
public class CountryService {
	
	
	@Autowired
	CountryRepository countryrep;
	
	public List<Country> getAllCountries() {
		return countryrep.findAll();
	}
	
	
	
	public Country getCountrybyId(int id) {
//		return countryrep.findById(id).get();
		
		List<Country> countries = countryrep.findAll();
		 Country country = null;
		 for(Country con:countries)
		 {
			 if(con.getId() == id)
				 country=con;
			 
		 }
		 return country;
	}
	
	public Country getCountrybyName(String countryName) {
		 List<Country> countries = countryrep.findAll();
		 Country country = null;
		 for(Country con:countries)
		 {
			 if(con.getCountryName().equalsIgnoreCase(countryName))
				 country=con;
			 
		 }
		 return country;
		 
	}
	
	
	public Country addCountry(Country country) {
		country.setId(getmaxId());
		countryrep.save(country);
		return country;
	}
	
	public  int getmaxId() {
		return countryrep.findAll().size()+1;
	}
	
	public Country updateCountry(Country country) {
		countryrep.save(country);
		return country;
	}
	
	public Country  deleteCountry(Country  country) {
		countryrep.delete(country);
		return country;
		
		
	}
	
}
