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
    private int id;

    private int sitAndReachTest;

    @Transient
    private String result;

    @NotNull
    @ManyToOne
    private Athlete athlete;

    @Override
    public String toString() {
        return "SitAndReachTest [id=" + id + ", sitAndReachTest=" + sitAndReachTest + ", result=" + result
                + ", athleteId=" + athlete + "]";
    }

}
