package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.repo.AthleteRepo;
import lombok.val;
import org.springframework.stereotype.Service;

/**
 * @author Nyabinde Nyasha
 * @created 1/10/2021
 * @project fitness-be
 */

@Service
public class AthleteServiceHelperImpl extends BaseServiceImpl<Athlete, Athlete, Athlete> implements AthleteServiceHelper {

    private final AthleteRepo athleteRepo;

    public AthleteServiceHelperImpl(AthleteRepo athleteRepo) {
        super(athleteRepo);
        this.athleteRepo = athleteRepo;
    }

    @Override
    protected Class<Athlete> getEntityClass() {
        return Athlete.class;
    }

    @Override
    public Athlete create(Athlete createDto) {
        Athlete athleteAlreadySaved = athleteRepo.findByFullName(createDto.getFullName());

        if (athleteAlreadySaved != null)
            throw new com.minimum.local.InvalidRequestException("Username already taken");

        val athlete = Athlete.fromCommand(createDto);
        return athleteRepo.save(athlete);
    }

    @Override
    public Athlete findByFullName(String fullName) {
        return athleteRepo.findByFullName(fullName);
    }

    @Override
    public boolean existsByFullName(String fullName) {
        return athleteRepo.existsByFullName(fullName);
    }

    @Override
    public Athlete update(Athlete updateDto) {
        return null;
    }
}


