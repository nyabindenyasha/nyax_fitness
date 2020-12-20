package com.nyasha.fitnessapp.models;

import com.nyasha.fitnessapp.local.Gender;
import com.nyasha.fitnessapp.local.Position;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String fullName;

	@Column
	private Date date;

	@Column
	private String picture; // url

	@Column
	private String idNumber;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private Gender sex;

	@Column
	private Date dateOfBirth;

	@Column
	@Enumerated(EnumType.ORDINAL)
	private Position position;

	@Column
	private String team;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "BaseEntity [fullName=" + fullName + ", date=" + date + ", picture=" + picture + ", idNumber=" + idNumber
				+ ", sex=" + sex + ", dateOfBirth=" + dateOfBirth + ", position=" + position + ", team=" + team + "]";
	}

}
