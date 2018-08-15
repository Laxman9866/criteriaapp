package com.slokam.criteria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.slokam.criteria.pojo.PersonPojo;
import com.slokam.criteria.pojo.PersonSearch;

@Repository
public class PersonDAO {

	private static final Logger LOGGER=LogManager.getLogger(PersonDAO.class);
	
	@PersistenceContext
	private EntityManager em;
	 
	public List<PersonPojo> personCriteria(PersonSearch personSearch){
		LOGGER.trace("Enter into personCriteria in PersonDAO ");
		Session session=em.unwrap(Session.class);
		Criteria ctr=session.createCriteria(PersonPojo.class,"p");
		ctr.createAlias("p.passportPojo", "ppt");
		ctr.createAlias("p.country", "c");
		
		if(personSearch.getPersonName()!=null&& personSearch.getPersonName().trim().length()!=0)
		{
			ctr.add(Restrictions.like("p.personName", personSearch.getPersonName()+"%"));
			LOGGER.debug(" Person Name   :"+personSearch.getPersonName()+"%");
		}
		if(personSearch.getPassportNumber()!=null&& personSearch.getPassportNumber().trim().length()!=0)
		{
			ctr.add(Restrictions.like("ppt.passportNumber", personSearch.getPassportNumber()+"%"));
			LOGGER.debug(" Passport Number    :"+personSearch.getPassportNumber()+"%");
		}
		if(personSearch.getName()!=null&& personSearch.getName().trim().length()!=0)
		{
			ctr.add(Restrictions.like("c.name", personSearch.getName()+"%"));
			LOGGER.debug(" Country Name    :"+personSearch.getName()+"%");
		}
			
		List<PersonPojo> personList=ctr.list();
		LOGGER.info("Sucessfully returned Person List of Data");
		LOGGER.trace("Exit from personCriteria in PersonDAO ");
		return personList;
	}
}
