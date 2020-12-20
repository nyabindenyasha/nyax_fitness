package com.nyasha.fitnessapp.repo;

import com.nyasha.fitnessapp.models.Athlete;

import java.util.List;

public interface AthleteRepo extends BaseRepository<Athlete> {

    Athlete findByFullName(String fullName);

    List<Athlete> findByTeamId(int id);

}

