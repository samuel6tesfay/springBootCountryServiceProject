package com.example.demo.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Country;
import com.example.demo.services.CountryService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
//@RequestMapping("")
public class CountryController {
	
//	CountryService countryService = new CountryService();
	
    Logger logger = LoggerFactory.getLogger(CountryController.class);

	
	@Autowired
	CountryService countryService;
	
	@GetMapping("/")
	public ResponseEntity<List<Country>> getCountryies(){
		
		
		try {
			List<Country> countries = countryService.getAllCountries();
			return new ResponseEntity<List<Country>>(countries,HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getcountries/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value="id") int id){
		
		
		try {
			Country country = countryService.getCountrybyId(id);
			return new ResponseEntity<Country>(country,HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/getcountries/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String name){
		
		try {
			Country country = countryService.getCountrybyName(name);
			return new ResponseEntity<Country>(country,HttpStatus.FOUND);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		
		try {
			 country = countryService.addCountry(country);
			return new ResponseEntity<Country>(country,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}
	
	@PutMapping("/updatecountry/{id}")
	public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id,@RequestBody Country country) {
		
		try {
			
			Country existCountry = countryService.getCountrybyId(id);
			
			existCountry.setCountryName(country.getCountryName());
			existCountry.setCountryCapital(country.getCountryCapital());
			

			
			Country update_country =  countryService.updateCountry(existCountry);
			

			return new ResponseEntity<Country>(update_country,HttpStatus.OK);
			
		}catch(Exception e){
			return new  ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable(value="id") int id) {
		
		Country country = null;
		
		try {
			country = countryService.getCountrybyId(id);
			countryService.deleteCountry(country);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Country>(country,HttpStatus.OK);
	}
	
	
}
