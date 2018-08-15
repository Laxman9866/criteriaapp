package com.slokam.criteria.senddata;

import java.io.File;

import org.springframework.stereotype.Component;

import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.file.IFileGenerator;
import com.slokam.criteria.file.NameFileUtil;
@Component
public class FileGeneratorResponsibility extends AbSendDataResponsibility{

	@Override
	public void prepairResponsibilities(SendDataResponsibilityPojo pojo) {
		String finalfolder=pojo.getFolderPath()+System.currentTimeMillis()+File.separator;
		new File(finalfolder).mkdirs();
	IFileGenerator generator;
	try {
		generator = NameFileUtil.generateFile(pojo.getPersonSearch().getRequiredFile());
		generator.getFileGenerator(finalfolder, pojo.getPersons());
		
	} catch (FileGeneratorException e) {
		e.printStackTrace();
	}
	
	pojo.setFinalPath(finalfolder);
	nextResponsibility(pojo);
	}
}
