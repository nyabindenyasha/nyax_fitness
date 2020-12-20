package com.nyasha.fitnessapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyasha.fitnessapp.models.Teams;
import com.nyasha.fitnessapp.repo.TeamsRepo;

@Service
public class TeamsService {

	@Autowired
	private TeamsRepo teamsRepo;

	public List<Teams> findAll() {
		return (List<Teams>) teamsRepo.findAll();
	}

	public Teams findOne(long id) {
		return teamsRepo.findById(id).get();
	}

	public Teams findByName(String name) {
		return teamsRepo.findByName(name);
	}

	public Teams saveR(Teams cow) {
		return teamsRepo.save(cow);
	}

	public void save(Teams b) {
		teamsRepo.save(b);
	}

	public void delete(long id) {
		teamsRepo.deleteById(id);
	}

}
