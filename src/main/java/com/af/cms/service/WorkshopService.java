package com.af.cms.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.af.cms.model.Workshop;
import com.af.cms.repository.WorkshopRepository;
import com.af.cms.util.FileUtil;


@Service
public class WorkshopService {
	private static final Logger log = LoggerFactory.getLogger(WorkshopService.class);

	@Autowired
	public WorkshopRepository workshopRepository;

	@Autowired
	private SequenceGeneratorService service;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${email.username}")
	private String sendFromEmail;

	public Workshop saveWorkshop(Workshop workshop) throws IOException {

		try {
			
			

			workshop.setId(service.getSequenceNumber(Workshop.SEQUENCE_NAME));
			workshop.setDate(null);
			workshop.setTime(null);
			workshop.setIsApproved(false);
			

			return workshopRepository.insert(workshop);

		}catch (Exception e) {
			log.debug("Insert error:" + e);
			return null;
		} 
	}


	public List<Workshop> getAllWorkshop() { 

		return workshopRepository.findAll(); 
	}


	public int deleteWorkshop(int id) {

		try {
			workshopRepository.deleteById(id);
			return 1;
		}catch (Exception e) {
			log.info("Delete error: "+ e);
			return 0;
		}

	}
	
	
	


	public int updateApprovedStatus(int id) {

		try {
			Workshop workshop = new Workshop();

			workshop = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), Workshop.class);
			workshop.setIsApproved(true);
			String email = workshop.getEmail();
			log.info(email);
			mongoTemplate.save(workshop,"workshop");
			sendEmail(workshop, email);
			return 1;


		}catch (Exception e) {
			log.info("error" + e);
			return 0;
		}
	}


	public Workshop getPdfByid(int id) {
		
	  return workshopRepository.findById(id).get();
	}
	
	public int saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
         
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath);
            return 1;
        } catch (IOException ioe) { 
        	log.info("error " + ioe );
        	return 0;
          
           
        }      
    }

	
	public void sendEmail(Workshop workshop,String email) {
		
		String name = workshop.getLastName();
		String date = workshop.getDate();
		String time =  workshop.getTime();

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setFrom(sendFromEmail);

		msg.setSubject("Email of Approval for workshop coordinator to conduct the Workshop at the ICAF 2021");
		msg.setText( "Dear " + name + System.lineSeparator()+  System.lineSeparator()+   "We're sending you this email because you requested conduct to workshop in ICAF 2021,Our organization panel have "
				+ "approved your workshop propsal."
				+ System.lineSeparator()+  System.lineSeparator()+" Date: " + date 
				+ System.lineSeparator()+  System.lineSeparator()+"Time: " + time 
				+ System.lineSeparator()+  System.lineSeparator()+"Venue: " + "SLIIT AUDITORIUM" );

		javaMailSender.send(msg);

	}






}
