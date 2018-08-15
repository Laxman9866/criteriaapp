package com.slokam.criteria.file;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.slokam.criteria.dao.PersonDAO;
import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.pojo.PersonPojo;

public class NameFileUtil {
	private static final Logger LOGGER=LogManager.getLogger(NameFileUtil.class);
	public static String getName(PersonPojo personPojo){
		
		return personPojo.getId()+"-"+personPojo.getPersonName();
	}
	
	
	public static IFileGenerator generateFile(String fileType) throws FileGeneratorException{
		LOGGER.trace("Enter into generateFile in NameFileUtil ");
		LOGGER.debug(" File Type    :"+fileType);
		IFileGenerator generator=null;
		switch (fileType) {
		case "xml":
			generator =new XmlFileGenerator();
			LOGGER.info("Sucessfully returned xml type");
			break;

		case "xlsx":
			generator =new XcellFileGenerator();
			LOGGER.info("Sucessfully returned xlsx type");
			break;
		case "docx":
			generator =new WordFileGenerator();
			LOGGER.info("Sucessfully returned docx type");
			break;
		case "pdf":
			generator =new PdfFileReader();
			LOGGER.info("Sucessfully returned pdf type");
			break;
			default:throw new FileGeneratorException("Invalid Type");
			
		}
		LOGGER.trace("Exit from generateFile in NameFileUtil ");
		return generator;
	}
}
