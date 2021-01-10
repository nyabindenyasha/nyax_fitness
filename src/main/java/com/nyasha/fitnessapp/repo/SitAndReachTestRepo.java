package com.nyasha.fitnessapp.repo;

import com.nyasha.fitnessapp.models.SitAndReachTest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SitAndReachTestRepo extends BaseRepository<SitAndReachTest> {

    List<SitAndReachTest> findByAthlete_Team_Id(long teamId);

    boolean existsByAthlete_Id(long athleteId);

}


