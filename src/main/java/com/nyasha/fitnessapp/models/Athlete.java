package com.nyasha.fitnessapp.models;

import com.nyasha.fitnessapp.local.Gender;
import com.nyasha.fitnessapp.local.Position;
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

	@Override
	public String toString() {
		return "Athlete [id=" + id + ", fullName=" + fullName + ", date=" + date + ", picture=" + picture
				+ ", idNumber=" + idNumber + ", sex=" + sex + ", dateOfBirth=" + dateOfBirth + ", position=" + position
				+ ", team=" + team + "]";
	}

}
