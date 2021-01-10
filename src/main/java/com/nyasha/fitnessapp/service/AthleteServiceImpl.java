package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.local.enums.Role;
import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.models.UserAccount;
import com.nyasha.fitnessapp.repo.AthleteRepo;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nyabinde Nyasha
 * @created 1/10/2021
 * @project fitness-be
 */

@Service
public class AthleteServiceImpl extends BaseServiceImpl<Athlete, Athlete, Athlete> implements AthleteService {

    private final AthleteRepo athleteRepo;

    private final UserAccountService userAccountServiceHelper;

    public AthleteServiceImpl(AthleteRepo athleteRepo, UserAccountService userAccountServiceHelper) {
        super(athleteRepo);
        this.athleteRepo = athleteRepo;
        this.userAccountServiceHelper = userAccountServiceHelper;
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
    public Athlete update(Athlete updateDto) {

        boolean detailsExists = athleteRepo.existsByFullName(updateDto.getFullName());

        if (!detailsExists) {
            throw new com.minimum.local.InvalidRequestException("Athlete not found");
        }

        Athlete athlete = findById(updateDto.getId());

        athlete.update(updateDto);

        return athleteRepo.save(athlete);
    }

    @Override
    public List<Athlete> findByTeamId(long id) {
        return athleteRepo.findByTeamId(id);
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
    public List<Athlete> findByLoggedInUser(long userId) {

        List<Athlete> athleteList = new ArrayList<>();

        UserAccount userAccount = userAccountServiceHelper.findById(userId);

        if (userAccount.getRole() == Role.TEAM_USER) {
            return athleteList;
        } else if (userAccount.getRole() == Role.TEAM_ADMIN) {
            return athleteRepo.findByTeamId(userAccount.getTeamId());
        }

        return athleteRepo.findAll();
    }
}
