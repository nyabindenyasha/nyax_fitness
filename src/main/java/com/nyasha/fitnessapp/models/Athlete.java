package com.nyasha.fitnessapp.models;

import com.nyasha.fitnessapp.local.Gender;
import com.nyasha.fitnessapp.local.Position;
import com.nyasha.fitnessapp.local.enums.Role;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "athlete")
public class Athlete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String fullName;

    private Date date;

    private String picture; // url

    private String idNumber;

    @Enumerated(EnumType.ORDINAL)
    private Gender sex;

    private Date dateOfBirth;

    @Enumerated(EnumType.ORDINAL)
    private Position position;

    @NotNull
    @ManyToOne
    private Teams team;

    public static Athlete fromCommand(Athlete request) {

        if (request == null) {
            return null;
        }

        Athlete athlete = new Athlete();
        athlete.setFullName(request.getFullName());
        athlete.setPicture(request.getPicture());
        athlete.setDate(new Date());
        athlete.setTeam(request.getTeam());
        athlete.setIdNumber(request.getIdNumber());
        athlete.setSex(request.getSex());
        athlete.setDateOfBirth(request.getDateOfBirth());
        athlete.setPosition(request.getPosition());
        return athlete;
    }

    public static Athlete fromUserAccount(UserAccount request) {

        if (request == null || (request.getRole() != Role.TEAM_USER)) {
            return null;
        }

        Athlete athlete = new Athlete();
        athlete.setFullName(request.getFirstName() + " " + request.getLastName());
        athlete.setPicture("");
        athlete.setDate(new Date());
        athlete.setIdNumber("");
        athlete.setTeam(request.getTeam());
        athlete.setSex(Gender.OTHER);
        athlete.setDateOfBirth(new Date());
        athlete.setPosition(Position.DEFAULTY);
        return athlete;
    }

    public void update(Athlete updateRequest) {
        this.setFullName(updateRequest.getFullName());
        this.setPicture(updateRequest.getPicture());
        this.setIdNumber(updateRequest.getIdNumber());
        this.setSex(updateRequest.getSex());
        this.setDateOfBirth(updateRequest.getDateOfBirth());
        this.setPosition(updateRequest.getPosition());
    }

    @Override
    public String toString() {
        return "Athlete [id=" + id + ", fullName=" + fullName + ", date=" + date + ", picture=" + picture
                + ", idNumber=" + idNumber + ", sex=" + sex + ", dateOfBirth=" + dateOfBirth + ", position=" + position
                + ", team=" + team + "]";
    }

}
