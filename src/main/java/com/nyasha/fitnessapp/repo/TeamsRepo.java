package com.nyasha.fitnessapp.repo;

import com.nyasha.fitnessapp.models.Teams;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepo extends BaseRepository<Teams>{

	Teams findByName(String name);

	boolean existsByName(String name);
}


