package com.slokam.criteria.pojo;

public class PersonSearch {
	private String personName;
	private String passportNumber;
	private String name;
	private String requiredFile;
	private String mailType;
	public String getMailType() {
		return mailType;
	}
	public void setMailType(String mailType) {
		this.mailType = mailType;
	}
	public String getRequiredFile() {
		return requiredFile;
	}
	public void setRequiredFile(String requiredFile) {
		this.requiredFile = requiredFile;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
