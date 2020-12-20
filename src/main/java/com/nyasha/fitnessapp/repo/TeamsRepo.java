package com.nyasha.fitnessapp.repo;

import org.springframework.data.repository.CrudRepository;

import com.nyasha.fitnessapp.models.Teams;

public interface TeamsRepo extends BaseRepository<Teams>{
	
	Teams findByName(String name);
}


