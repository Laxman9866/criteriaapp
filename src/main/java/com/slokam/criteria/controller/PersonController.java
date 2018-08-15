package com.slokam.criteria.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slokam.criteria.exception.ApplicationMessageException;
import com.slokam.criteria.exception.FileGeneratorException;
import com.slokam.criteria.file.IFileGenerator;
import com.slokam.criteria.file.NameFileUtil;
import com.slokam.criteria.message.IMessageSender;
import com.slokam.criteria.message.MailMessageUtil;
import com.slokam.criteria.pojo.PersonPojo;
import com.slokam.criteria.pojo.PersonSearch;
import com.slokam.criteria.senddata.AbSendDataResponsibility;
import com.slokam.criteria.senddata.SendDataResponsibilityPojo;
import com.slokam.criteria.senddata.SendDataUtil;
import com.slokam.criteria.service.PersonService;
import com.slokam.criteria.thread.MailThread;

@RestController
@CrossOrigin
@RequestMapping("Person")
public class PersonController {
 
	@Autowired
	private MailMessageUtil mailMessageUtil;
	@Autowired
	private SendDataUtil sendDataUtil;
	
	private static final Logger LOGGER=LogManager.getLogger(PersonController.class);
	
	@Value("${genfolder.location:E:\\fileupload\\xml\\xml\\}")
	private String genfolder;
	@Autowired
	private PersonService personService;
	
	@Autowired
	private JavaMailSender mailsender;
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ResponseEntity<List<PersonPojo>> personCriteria(@RequestBody PersonSearch personSearch) throws FileGeneratorException, ApplicationMessageException {
		List<PersonPojo> persons=personService.personCriteria(personSearch);
		/*SendDataResponsibilityPojo pojo=new SendDataResponsibilityPojo();
		pojo.setFolderPath(genfolder);
		pojo.setPersons(persons);
		pojo.setPersonSearch(personSearch);
		AbSendDataResponsibility resp=sendDataUtil.sendResps();
		resp.prepairResponsibilities(pojo);*/
		
		ResponseEntity<List<PersonPojo>> re=new ResponseEntity<List<PersonPojo>>(persons,HttpStatus.OK);
		LOGGER.trace("Exit from personCriteria in PersonController ");
		return re;
	}
	/*
	 * LOGGER.trace("Enter into personCriteria in PersonController ");
		LOGGER.debug("Generated Folder :"+genfolder);
		String finalfolder=genfolder+System.currentTimeMillis()+File.separator;
		LOGGER.debug("Final Folder :"+finalfolder);
		new File(finalfolder).mkdirs();
		List<PersonPojo> persons=personService.personCriteria(personSearch);
		LOGGER.debug(" Person Records :"+persons.size());
		IFileGenerator generator=NameFileUtil.generateFile(personSearch.getRequiredFile());
		LOGGER.debug(" Generator Object :"+generator);
		generator.getFileGenerator(finalfolder, persons);
		
		try {
			JAXBContext context=JAXBContext.newInstance(PersonPojo.class);
			Marshaller marshaller=context.createMarshaller();
			
			for (PersonPojo personPojo : persons) {
				FileOutputStream fos=new FileOutputStream(finalfolder+personPojo.getId()+"-"+personPojo.getPersonName()+".xml");
				marshaller.marshal(personPojo, fos);
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		File file =new File(finalfolder);
		String[] filenames=file.list();
		LOGGER.debug(" File Names :"+filenames);
		FileOutputStream fos;
		try {
			LOGGER.debug(" Zip   :"+genfolder+"Zipfloder.zip");
			 fos=new FileOutputStream(genfolder+"Zipfloder.zip");
			 ZipOutputStream zos=new ZipOutputStream(fos);
			 for(String name:filenames){
				 ZipEntry entry=new ZipEntry(name);
				 zos.putNextEntry(entry);
				 Path path=Paths.get(finalfolder+name);
				byte[] bytes= Files.readAllBytes(path);
				zos.write(bytes);
			 }
			 LOGGER.info("Sucessfully Zip File Created");
			 zos.close();
			 fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("laxman.lucky005@gmail.com");
		message.setTo("laxman.lucky005@gmail.com");
		message.setText("Test");
		message.setSubject("Hello");
		mailsender.send(message);
		
		
		
		MailThread thread=new MailThread(mailsender, genfolder);
		 LOGGER.info("Sucessfully Sending Mial to Zip Folder");
		thread.start();
		try {
			MimeMessage mimeMessage=mailsender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper =new MimeMessageHelper(mimeMessage,true);
			mimeMessageHelper.setTo("laxman.lucky005@gmail.com");
			mimeMessageHelper.setFrom("laxman.lucky005@gmail.com");
			mimeMessageHelper.setText("Find Attachment");
			mimeMessageHelper.setSubject("Attachment File");
			FileSystemResource fsr=new FileSystemResource(genfolder+"Zipfloder.zip");
			mimeMessageHelper.addAttachment("Zipfloder.zip", fsr);
			mailsender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		IMessageSender messageSender=mailMessageUtil.sendMessageType(personSearch.getMailType());
		messageSender.sendMessages(genfolder);
	 */
	 
}
