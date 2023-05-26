package com.matheuscordeiro.domain;

import java.util.Date;

import org.apache.logging.log4j.util.Strings;

public class Person {
	private int id;
	private String name;
	private String email;
	private Date dateOfBirth;
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isValid() {
		return !Strings.isBlank(name) && !Strings.isBlank(email) && dateOfBirth != null;
	}

}
