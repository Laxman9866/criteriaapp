package com.slokam.criteria.senddata;

import java.util.List;

import com.slokam.criteria.pojo.PersonPojo;
import com.slokam.criteria.pojo.PersonSearch;
import com.slokam.criteria.service.PersonService;

public class SendDataResponsibilityPojo {

	private String folderPath;
	private List<PersonPojo> persons;
	private String finalPath;
	public String getFolderPath() {
		return folderPath;
	}
	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}
	private String zipFilePath;
	
	private PersonSearch personSearch;
	public String getFinalPath() {
		return finalPath;
	}
	public void setFinalPath(String finalPath) {
		this.finalPath = finalPath;
	}
	public List<PersonPojo> getPersons() {
		return persons;
	}
	public void setPersons(List<PersonPojo> persons) {
		this.persons = persons;
	}
	public String getZipFilePath() {
		return zipFilePath;
	}
	public void setZipFilePath(String zipFilePath) {
		this.zipFilePath = zipFilePath;
	}
	
	public PersonSearch getPersonSearch() {
		return personSearch;
	}
	public void setPersonSearch(PersonSearch personSearch) {
		this.personSearch = personSearch;
	}
}
