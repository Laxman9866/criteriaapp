package com.slokam.criteria.senddata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;
@Component
public class ZipFileResponsibility extends AbSendDataResponsibility{

	@Override
	public void prepairResponsibilities(SendDataResponsibilityPojo pojo) {
		File file =new File(pojo.getFinalPath());
		String[] filenames=file.list();
		String zipFinalCompletePath=pojo.getFolderPath()+System.currentTimeMillis()+".zip";
		FileOutputStream fos;
		try {
			 fos=new FileOutputStream(zipFinalCompletePath);
			 ZipOutputStream zos=new ZipOutputStream(fos);
			 for(String name:filenames){
				 ZipEntry entry=new ZipEntry(name);
				 zos.putNextEntry(entry);
				 Path path=Paths.get(pojo.getFinalPath()+name);
				byte[] bytes= Files.readAllBytes(path);
				zos.write(bytes);
			 }
			 zos.close();
			 fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pojo.setZipFilePath(zipFinalCompletePath);
		nextResponsibility(pojo);
	}

}
