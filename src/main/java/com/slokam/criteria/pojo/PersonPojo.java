package com.slokam.criteria.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="person")
@XmlRootElement
public class PersonPojo {

	@Id
	@GeneratedValue
	private Integer id;
	private String personName;
	private Integer personAge;
	@ManyToOne
	@JoinColumn(name="fkcid")
	
	private CountryPojo country=new CountryPojo();
	@XmlElement
	public CountryPojo getCountry() {
		return country;
	}
	public void setCountry(CountryPojo country) {
		this.country = country;
	}
	@OneToOne(mappedBy="person",cascade=CascadeType.ALL)
	private PassportPojo passportPojo=new PassportPojo();
	@XmlElement
	public PassportPojo getPassportPojo() {
		return passportPojo;
	}
	public void setPassportPojo(PassportPojo passportPojo) {
		this.passportPojo = passportPojo;
	}
	
	@XmlAttribute
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@XmlElement
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	@XmlElement
	public Integer getPersonAge() {
		return personAge;
	}
	public void setPersonAge(Integer personAge) {
		this.personAge = personAge;
	}
	
	
}
