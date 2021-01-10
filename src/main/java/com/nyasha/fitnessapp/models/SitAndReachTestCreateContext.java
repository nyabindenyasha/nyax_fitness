package com.nyasha.fitnessapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author Nyabinde Nyasha
 * @created 1/10/2021
 * @project fitness-be
 */

@Data
public class SitAndReachTestCreateContext {

    private long id;

    private int sitAndReachTest;

    @JsonIgnore
    private Athlete athlete;

    private long athleteId;
}
