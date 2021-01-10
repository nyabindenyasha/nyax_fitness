package com.nyasha.fitnessapp.repo;

import com.nyasha.fitnessapp.models.Athlete;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepo extends BaseRepository<Athlete> {

    Athlete findByFullName(String fullName);

    boolean existsByFullName(String fullName);

    List<Athlete> findByTeamId(long id);

}


