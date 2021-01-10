package com.nyasha.fitnessapp.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "sit_and_reach_test")
public class SitAndReachTest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int sitAndReachTest;

    @Transient
    private String result;

    @NotNull
    @ManyToOne
    private Athlete athlete;


    public static SitAndReachTest fromCommand(SitAndReachTestCreateContext request) {

        if (request == null) {
            return null;
        }

        SitAndReachTest sitAndReachTest = new SitAndReachTest();
        sitAndReachTest.setSitAndReachTest(request.getSitAndReachTest());
        sitAndReachTest.setAthlete(request.getAthlete());
        return sitAndReachTest;
    }

    public void update(SitAndReachTestCreateContext updateRequest) {
        this.setSitAndReachTest(updateRequest.getSitAndReachTest());
    }

    @Override
    public String toString() {
        return "SitAndReachTest [id=" + id + ", sitAndReachTest=" + sitAndReachTest + ", result=" + result
                + ", athleteId=" + athlete + "]";
    }

}
