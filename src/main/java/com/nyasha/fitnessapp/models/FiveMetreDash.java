package com.nyasha.fitnessapp.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "five_metre_dash")
public class FiveMetreDash implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double reaction;

    @Transient
    private String result;

    @NotNull
    @ManyToOne
    private Athlete athlete;

    @Override
    public String toString() {
        return "FiveMetreDash [id=" + id + ", reaction=" + reaction + ", result=" + result + ", athleteId=" + athlete
                + "]";
    }

}
