package com.slokam.criteria.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.pojo.PersonPojo;

public class WordFileGenerator implements IFileGenerator{
	private static final Logger LOGGER=LogManager.getLogger(WordFileGenerator.class);
	@Override
	public void getFileGenerator(String outputPath, List<PersonPojo> personList) throws FileGeneratorException {
		LOGGER.trace("Enter into getFileGenerator in WordFileGenerator ");
		for (PersonPojo person : personList) {
			XWPFDocument document=new XWPFDocument();
			XWPFParagraph paragraph=document.createParagraph();
			XWPFRun run=paragraph.createRun();
			run.setText("Person Details");
			run.setText(person.getId()+":"+person.getPersonName()+":"+person.getPersonAge());
			System.out.println("/n");
			run.setText("Passport Details");
			run.setText(person.getPassportPojo().getId()+":"+person.getPassportPojo().getPassportNumber()+":"
			+person.getPassportPojo().getPassportIssueDate()+":"+person.getPassportPojo().getPassportExpireDate());
			System.out.println("/n");
			run.setText("Country Details");
			run.setText(person.getCountry().getId()+":"+person.getCountry().getName()+":"+person.getCountry().getDesc());
			FileOutputStream fos=null;
			try {
				fos=new FileOutputStream(outputPath+NameFileUtil.getName(person)+".docx");
				LOGGER.debug(" Folder with Name   :"+outputPath+NameFileUtil.getName(person)+".docx");
				document.write(fos);
				LOGGER.info("Sucessfully Created Word File");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				throw new FileGeneratorException("Word -- FileNotFoundException", e);
				
				
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				throw new FileGeneratorException("Word -- IOException", e);
			}finally {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					throw new FileGeneratorException("Word -- IOException", e);
				}
			}
			
		}
		LOGGER.trace("Exit from getFileGenerator in WordFileGenerator ");
	}

}
