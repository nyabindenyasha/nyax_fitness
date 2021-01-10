package com.nyasha.fitnessapp;

import com.nyasha.fitnessapp.local.Gender;
import com.nyasha.fitnessapp.local.Position;
import com.nyasha.fitnessapp.models.Athlete;
import com.nyasha.fitnessapp.models.FiveMetreDash;
import com.nyasha.fitnessapp.models.SitAndReachTestCreateContext;
import com.nyasha.fitnessapp.models.Teams;
import com.nyasha.fitnessapp.service.*;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nyabinde Nyasha
 * @created 12/27/2020
 * @project procurement-system
 */

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserAccountService userAccountService;

    private final TeamsService teamsService;

    private final SitAndReachTestService sitAndReachTestService;

    private final FiveMetreDashService fiveMetreDashService;

    private final AthleteServiceHelper athleteService;

    CommandLineAppStartupRunner(UserAccountService userAccountService, TeamsService teamsService, SitAndReachTestService sitAndReachTestService, FiveMetreDashService fiveMetreDashService, AthleteServiceHelper athleteService) {
        this.userAccountService = userAccountService;
        this.teamsService = teamsService;
        this.sitAndReachTestService = sitAndReachTestService;
        this.fiveMetreDashService = fiveMetreDashService;
        this.athleteService = athleteService;
    }

    @Override
    public void run(String... args) throws Exception {

        try {

            val teamExists = teamsService.existsByName("Sample Team");

            if (!teamExists) {
                Teams teams = new Teams();
                teams.setName("Sample Team");
                teams.setDescription("Sample Team");
                teamsService.save(teams);
            }

            val athleteExists = athleteService.existsByFullName("Demo Player1");

            if (!athleteExists) {

                val team = teamsService.findByName("Sample Team");

                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date date = format.parse("26/10/1985");

                Athlete player = new Athlete();
                player.setDate(new Date());
                player.setDateOfBirth(date);
                player.setFullName("Demo Player1");
                player.setIdNumber("09-77766 M 88");
                player.setPosition(Position.DEFENDER);
                player.setSex(Gender.FEMALE);
                player.setTeam(team);
                Athlete playerSaved = athleteService.create(player);

                FiveMetreDash fiveMetreDash = new FiveMetreDash();
                fiveMetreDash.setAthlete(playerSaved);
                fiveMetreDash.setReaction(0.9);
                fiveMetreDashService.save(fiveMetreDash);

                SitAndReachTestCreateContext sitAndReachTest = new SitAndReachTestCreateContext();
                sitAndReachTest.setSitAndReachTest(18);
                sitAndReachTest.setAthlete(playerSaved);
                sitAndReachTestService.create(sitAndReachTest);

            }

        } catch (Exception e) {
            System.out.println("Exception @ CommandLineRunner: " + e.getMessage());
        }
    }
}
