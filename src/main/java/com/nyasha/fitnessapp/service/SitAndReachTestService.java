package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.models.SitAndReachTest;
import com.nyasha.fitnessapp.repo.SitAndReachTestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitAndReachTestService {

    @Autowired
    private SitAndReachTestRepo sitAndReachRepo;

    public List<SitAndReachTest> findAll() {
        return (List<SitAndReachTest>) sitAndReachRepo.findAll();
    }

    public SitAndReachTest findOne(long id) {
        return sitAndReachRepo.findById(id).get();
    }

    public SitAndReachTest saveR(SitAndReachTest cow) {
        return sitAndReachRepo.save(cow);
    }

    public void save(SitAndReachTest b) {
        sitAndReachRepo.save(b);
    }

    public void delete(long id) {
        sitAndReachRepo.deleteById(id);
    }

}
