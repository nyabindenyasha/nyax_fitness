package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.models.Athlete;

/**
 * @author Nyabinde Nyasha
 * @created 1/10/2021
 * @project fitness-be
 */

public interface AthleteServiceHelper extends BaseService<Athlete, Athlete, Athlete> {

    Athlete findByFullName(String fullName);

    boolean existsByFullName(String fullName);

}
