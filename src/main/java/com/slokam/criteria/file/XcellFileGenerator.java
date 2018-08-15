package com.slokam.criteria.file;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.pojo.PersonPojo;

public class XcellFileGenerator implements IFileGenerator{
	private static final Logger LOGGER=LogManager.getLogger(XcellFileGenerator.class);
	
	@Override
	public void getFileGenerator(String outputPath, List<PersonPojo> personList)throws FileGeneratorException {
		LOGGER.trace("Enter into getFileGenerator in XcellFileGenerator ");
		for(PersonPojo person:personList){
			XSSFWorkbook workbook=new XSSFWorkbook();
			XSSFSheet sheet=workbook.createSheet(person.getPersonName());
			XSSFRow row1=sheet.createRow(0);
			row1.createCell(0).setCellValue("Person Details");
			XSSFRow row2=sheet.createRow(1);
			row2.createCell(0).setCellValue("Id");
			row2.createCell(1).setCellValue(person.getId());
			XSSFRow row3=sheet.createRow(2);
			row3.createCell(0).setCellValue("Person Name");
			row3.createCell(1).setCellValue(person.getPersonName());
			XSSFRow row4=sheet.createRow(3);
			row4.createCell(0).setCellValue("Person Age");
			row4.createCell(1).setCellValue(person.getPersonAge());
			
			XSSFRow row5=sheet.createRow(4);
			row5.createCell(0).setCellValue("Passport Details");
			XSSFRow row6=sheet.createRow(5);
			row6.createCell(0).setCellValue("Id");
			row6.createCell(1).setCellValue(person.getPassportPojo().getId());
			XSSFRow row7=sheet.createRow(6);
			row7.createCell(0).setCellValue("Passport Number");
			row7.createCell(1).setCellValue(person.getPassportPojo().getPassportNumber());
			XSSFRow row8=sheet.createRow(7);
			row8.createCell(0).setCellValue("Passport Issue Date");
			row8.createCell(1).setCellValue(person.getPassportPojo().getPassportIssueDate());
			XSSFRow row9=sheet.createRow(8);
			row9.createCell(0).setCellValue("Passport Expire Date");
			row9.createCell(1).setCellValue(person.getPassportPojo().getPassportExpireDate());
			
			XSSFRow row10=sheet.createRow(9);
			row10.createCell(0).setCellValue("Country Details");
			XSSFRow row11=sheet.createRow(10);
			row11.createCell(0).setCellValue("Id");
			row11.createCell(1).setCellValue(person.getCountry().getId());
			XSSFRow row12=sheet.createRow(11);
			row12.createCell(0).setCellValue("Name");
			row12.createCell(1).setCellValue(person.getCountry().getName());
			XSSFRow row13=sheet.createRow(12);
			row13.createCell(0).setCellValue("Desc");
			row13.createCell(1).setCellValue(person.getCountry().getDesc());
			
			FileOutputStream fos=null;
			try {
				
				fos=new FileOutputStream(outputPath+NameFileUtil.getName(person)+".xlsx");
				LOGGER.debug(" Folder with Name   :"+outputPath+NameFileUtil.getName(person)+".xlsx");
				workbook.write(fos);
				LOGGER.info("Sucessfully Created Xcell File");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				throw new FileGeneratorException("Xcell -- FileNotFoundException", e);
			} catch (IOException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				throw new FileGeneratorException("Xcell -- IOException", e);
			}finally {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					throw new FileGeneratorException("Xcell -- IOException", e);
				}
			}
			
	

			
		}
		LOGGER.trace("Exit from getFileGenerator in XcellFileGenerator ");
	}

}
