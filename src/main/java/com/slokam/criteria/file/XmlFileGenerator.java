package com.slokam.criteria.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.pojo.PersonPojo;

public class XmlFileGenerator implements IFileGenerator{
	private static final Logger LOGGER=LogManager.getLogger(XmlFileGenerator.class);
	
	@Override
	public void getFileGenerator(String outputPath, List<PersonPojo> personList)throws FileGeneratorException {
		LOGGER.trace("Enter into getFileGenerator in XmlFileGenerator ");
		try {
			JAXBContext context=JAXBContext.newInstance(PersonPojo.class);
			Marshaller marshaller=context.createMarshaller();
			
			for (PersonPojo personPojo : personList) {
				FileOutputStream fos=new FileOutputStream(outputPath+NameFileUtil.getName(personPojo)+".xml");
				LOGGER.debug(" Folder with Name   :"+outputPath+NameFileUtil.getName(personPojo)+".xml");
				marshaller.marshal(personPojo, fos);
				LOGGER.info("Sucessfully Created XML File");
			}
		} catch (JAXBException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new FileGeneratorException("Xml -- JAXBException", e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new FileGeneratorException("Xml -- FileNotFoundException", e);
		}
		LOGGER.trace("Exit from getFileGenerator in XmlFileGenerator ");
	}

}
