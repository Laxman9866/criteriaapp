package com.slokam.criteria.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.pojo.PersonPojo;

public class PdfFileReader implements IFileGenerator{
	private static final Logger LOGGER=LogManager.getLogger(PdfFileReader.class);
	@Override
	public void getFileGenerator(String outputPath, List<PersonPojo> personList) throws FileGeneratorException{
		LOGGER.trace("Enter into getFileGenerator in PdfFileReader ");
		for (PersonPojo person : personList) {
			Document document=new Document();
			
			FileOutputStream fos=null;
			try {
				
				fos=new FileOutputStream(outputPath+NameFileUtil.getName(person)+".pdf");
				LOGGER.debug(" Folder with Name   :"+outputPath+NameFileUtil.getName(person)+".pdf");
			PdfWriter.getInstance(document, fos);
			document.open();
			Paragraph paragraph=new Paragraph("PerSon Details"+person.getId()+":"+person.getPersonName()+":"+
			person.getPersonAge()+"::"+"Passport Details"+
					":"+person.getPassportPojo().getId()+":"+
					person.getPassportPojo().getPassportNumber()+":"+
			person.getPassportPojo().getPassportIssueDate()+":"+person.getPassportPojo().getPassportExpireDate()
			+"::"+"Country Details"+":"+person.getCountry().getId()+":"+person.getCountry().getName()+":"+
			person.getCountry().getDesc());
			document.add(paragraph);
			document.close();
			LOGGER.info("Sucessfully Created PDF File");
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			
				throw new FileGeneratorException("Pdf -- FileNotFoundException", e);
			} catch (DocumentException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				throw new FileGeneratorException("Pdf -- DocumentException", e);
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				throw new FileGeneratorException("Pdf -- IOException", e);
			}
				
			finally {
				try {
					if(fos!=null)
						fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					throw new FileGeneratorException("Pdf -- IOException", e);
				}
			}
			}
		LOGGER.trace("Exit from getFileGenerator in PdfFileReader ");
		}
		
	}


