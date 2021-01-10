package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.models.Athlete;

import java.util.List;

public interface AthleteService extends BaseService<Athlete, Athlete, Athlete> {

    List<Athlete> findByTeamId(long id);

    Athlete findByFullName(String fullName);

    boolean existsByFullName(String fullName);

    List<Athlete> findByLoggedInUser(long id);

}
