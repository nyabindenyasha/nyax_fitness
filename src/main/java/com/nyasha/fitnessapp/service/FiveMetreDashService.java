package com.nyasha.fitnessapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyasha.fitnessapp.models.FiveMetreDash;
import com.nyasha.fitnessapp.repo.FiveMetreDashRepo;

@Service
public class FiveMetreDashService {

	@Autowired
	private FiveMetreDashRepo fiveMetreDashRepo;

	public List<FiveMetreDash> findAll() {
		return (List<FiveMetreDash>) fiveMetreDashRepo.findAll();
	}

	public FiveMetreDash findOne(long id) {
		return fiveMetreDashRepo.findById(id).get();
	}

	public FiveMetreDash saveR(FiveMetreDash cow) {
		return fiveMetreDashRepo.save(cow);
	}

	public void save(FiveMetreDash b) {
		fiveMetreDashRepo.save(b);
	}

	public void delete(long id) {
		fiveMetreDashRepo.deleteById(id);
	}

}
