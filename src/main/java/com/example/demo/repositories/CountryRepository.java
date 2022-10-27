package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.beans.Country;

public interface   CountryRepository extends JpaRepository<Country,Integer>{
	
}
