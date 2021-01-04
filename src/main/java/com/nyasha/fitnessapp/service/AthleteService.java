package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.repo.AthleteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepo athleteRepo;

    public List<Athlete> findAll() {
        return (List<Athlete>) athleteRepo.findAll();
    }

    public List<Athlete> findByTeamId(long id) {
        return athleteRepo.findByTeamId(id);
    }

    public Athlete findOne(long id) {
        return athleteRepo.findById(id).get();
    }

    public Athlete findByFullName(String fullName) {
        return athleteRepo.findByFullName(fullName);
    }

    public Athlete saveR(Athlete cow) {
        return athleteRepo.save(cow);
    }

    public void save(Athlete b) {
        athleteRepo.save(b);
    }

    public void delete(long id) {
        athleteRepo.deleteById(id);
    }

    public boolean existsByFullName(String fullName) {
        return athleteRepo.existsByFullName(fullName);
    }

}
