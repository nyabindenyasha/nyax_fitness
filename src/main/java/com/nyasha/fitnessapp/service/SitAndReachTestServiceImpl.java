package com.nyasha.fitnessapp.service;

import com.minimum.local.InvalidRequestException;
import com.nyasha.fitnessapp.local.Utils;
import com.nyasha.fitnessapp.local.enums.Role;
import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.models.SitAndReachTest;
import com.nyasha.fitnessapp.models.SitAndReachTestCreateContext;
import com.nyasha.fitnessapp.models.UserAccount;
import com.nyasha.fitnessapp.repo.SitAndReachTestRepo;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Nyabinde Nyasha
 * @created 1/10/2021
 * @project fitness-be
 */

@Service
public class SitAndReachTestServiceImpl extends BaseServiceImpl<SitAndReachTest, SitAndReachTestCreateContext, SitAndReachTestCreateContext> implements SitAndReachTestService {

    private final SitAndReachTestRepo sitAndReachTestRepo;

    private final UserAccountServiceHelper userAccountServiceHelper;

    private final AthleteService athleteService;

    public SitAndReachTestServiceImpl(SitAndReachTestRepo sitAndReachTestRepo, UserAccountServiceHelper userAccountServiceHelper, AthleteService athleteService) {
        super(sitAndReachTestRepo);
        this.sitAndReachTestRepo = sitAndReachTestRepo;
        this.userAccountServiceHelper = userAccountServiceHelper;
        this.athleteService = athleteService;
    }

    @Override
    protected Class<SitAndReachTest> getEntityClass() {
        return SitAndReachTest.class;
    }

    @Override
    public Collection<SitAndReachTest> findByLoggedInUser(long userId) {

        List<SitAndReachTest> sitAndReachTests = new ArrayList<>();

        UserAccount userAccount = userAccountServiceHelper.findById(userId);

        if (userAccount.getRole() == Role.TEAM_USER) {
            return sitAndReachTests;
        } else if (userAccount.getRole() == Role.TEAM_ADMIN) {

            val sitAndReachFromRepo = sitAndReachTestRepo.findByAthlete_Team_Id(userAccount.getTeamId());

            sitAndReachTests.addAll(sitAndReachFromRepo);

            for (SitAndReachTest x : sitAndReachTests) {
                x.setResult(Utils.getSitAndReachTest(x.getSitAndReachTest()));
            }

            return sitAndReachTests;
        } else {
            val allSitAndReachFromRepo = sitAndReachTestRepo.findAll();
            sitAndReachTests.addAll(allSitAndReachFromRepo);

            for (SitAndReachTest x : sitAndReachTests) {
                x.setResult(Utils.getSitAndReachTest(x.getSitAndReachTest()));
            }

            return sitAndReachTests;
        }
//        return sitAndReachTests;
    }

    @Override
    public SitAndReachTest create(SitAndReachTestCreateContext createDto) {

        boolean detailsExists = sitAndReachTestRepo.existsByAthlete_Id(createDto.getAthleteId());

        if (detailsExists) {
            throw new InvalidRequestException("SitAndReachTest record for athlete already exists");
        }

        Athlete athlete = athleteService.findById(createDto.getAthleteId());
        createDto.setAthlete(athlete);

        SitAndReachTest role = SitAndReachTest.fromCommand(createDto);

        return sitAndReachTestRepo.save(role);
    }

    @Override
    public SitAndReachTest update(SitAndReachTestCreateContext updateDto) {

        SitAndReachTest sitAndReachTest = findById(updateDto.getId());

        sitAndReachTest.update(updateDto);

        return sitAndReachTestRepo.save(sitAndReachTest);
    }

}
