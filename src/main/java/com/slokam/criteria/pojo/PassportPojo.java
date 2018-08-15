package com.slokam.criteria.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="passport")
@XmlRootElement
public class PassportPojo {

	@Id
	@GeneratedValue
	private Integer id;
	private String passportNumber;
	private String passportIssueDate;
	private String passportExpireDate;
	@OneToOne
	@JoinColumn(name="fkpid")
	@JsonIgnore
	private PersonPojo person;
	@XmlTransient
	public PersonPojo getPerson() {
		return person;
	}
	public void setPerson(PersonPojo person) {
		this.person = person;
	}
	@XmlAttribute
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlElement
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	@XmlElement
	public String getPassportIssueDate() {
		return passportIssueDate;
	}
	public void setPassportIssueDate(String passportIssueDate) {
		this.passportIssueDate = passportIssueDate;
	}
	@XmlElement
	public String getPassportExpireDate() {
		return passportExpireDate;
	}
	public void setPassportExpireDate(String passportExpireDate) {
		this.passportExpireDate = passportExpireDate;
	}
	
}
