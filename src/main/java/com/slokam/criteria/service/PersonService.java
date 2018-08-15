package com.slokam.criteria.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slokam.criteria.dao.PersonDAO;
import com.slokam.criteria.message.MailMessageUtil;
import com.slokam.criteria.pojo.PersonPojo;
import com.slokam.criteria.pojo.PersonSearch;
import com.slokam.criteria.senddata.AbSendDataResponsibility;
import com.slokam.criteria.senddata.SendDataResponsibilityPojo;
import com.slokam.criteria.senddata.SendDataUtil;

@Service
public class PersonService {
	private static final Logger LOGGER=LogManager.getLogger(PersonService.class);

	@Autowired
	private MailMessageUtil mailMessageUtil;
	@Autowired
	private SendDataUtil sendDataUtil;
	
	@Autowired
	private PersonDAO personDAO;
	@Value("${genfolder.location:E:\\fileupload\\xml\\xml\\}")
	private String genfolder;
	
	public List<PersonPojo> personCriteria(PersonSearch personSearch){
		LOGGER.trace("Enter into personCriteria in PersonService ");
		List<PersonPojo> persons=personDAO.personCriteria(personSearch);
		SendDataResponsibilityPojo pojo=new SendDataResponsibilityPojo();
		pojo.setFolderPath(genfolder);
		pojo.setPersons(persons);
		pojo.setPersonSearch(personSearch);
		AbSendDataResponsibility resp=sendDataUtil.sendResps();
		resp.prepairResponsibilities(pojo);
		LOGGER.info("Sucessfully returned Person List of Data");
		LOGGER.trace("Exit from personCriteria in PersonService ");
		
		return persons;
	}
}
