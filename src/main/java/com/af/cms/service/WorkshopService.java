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

import com.af.cms.dto.DateTime;
import com.af.cms.model.Keynotes;
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
			
			return 1;


		}catch (Exception e) {
			log.info("error" + e);
			return 0;
		}
	}


	public Workshop getWorkshopByid(int id) {
	   Workshop workshop = new Workshop();
	   try {
		workshop	=  workshopRepository.findById(id).get();
		return workshop;
	
	}catch (Exception e) {
		log.info("error" + e);
		return null;
	}
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

		msg.setSubject("Email of Approval for Workshop coordinator to conduct the workshop at the ICAF 2021.");
		msg.setText( "Dear " + name + System.lineSeparator()+  System.lineSeparator()+   "We are pleased to inform you that your Workshop Proposal, "
				+ "How to deal with procrastination has approved for present at the ICAF 2021."
				+ System.lineSeparator()+"I have mentioned the date, time and the venue below:"
				+  System.lineSeparator()+" Date: " + date 
				+  System.lineSeparator()+"Time: " + time 
				+  System.lineSeparator()+"Venue: " + "SLIIT AUDITORIUM" 
				+  System.lineSeparator()+  System.lineSeparator()+"visit ICAF Official Web Site for more details"
				+ System.lineSeparator()+  System.lineSeparator()+"Thank you");
		javaMailSender.send(msg);

	}
	
	
	public Workshop updateWorkshop(Workshop workshop,int id) {

		 try {
	            Optional<Workshop> workshop1 = workshopRepository.findById(id);
	            if (workshop1.equals(null) ) {
	                return null;
	            } else {
	                Workshop workshop2= workshop1.get();
	                workshop2.setFirstName(workshop.getFirstName());
	                workshop2.setTitle(workshop.getTitle());
	                workshop2.setUserRole(workshop2.getUserRole());
	                workshop2.setTime(workshop.getTime());
	                workshop2.setPassword(workshop.getPassword());
	                workshop2.setLastName(workshop.getLastName());
	                workshop2.setFileUrl(workshop2.getFileUrl());
	                workshop2.setDescription(workshop2.getDescription());
	                workshop2.setDate(workshop.getDate());
	                workshop2.setCountry(workshop.getCountry());
	                workshop2.setEmail(workshop.getEmail());
	               
	                
	               

	                workshopRepository.save(workshop2);
	               System.out.println("eee" + workshop2 + "/n" + workshop);
	                return workshop2;
	            }
	        } catch (Exception e) {
	        	log.info("error " + e);
	            return null;
	        }
	
		
	}
	
	
	
    public List<Workshop> getAllApprovedWorkshop(){
		
		try {

			List<Workshop> list  = mongoTemplate.find(Query.query(Criteria.where("is_approved").is(true)), Workshop.class);
			return list;


		}catch (Exception e) {
			log.info("error" + e);
			return null;
		}
		
			}
    
    
    
    public Workshop approveResearchpaper(int id,DateTime dateTime) {
    	
    	
    	 try {
    		 
    		 
	            Optional<Workshop> workshop1 = workshopRepository.findById(id);
	            if (workshop1 == null) {
	                return null;
	            } else {
	                Workshop workshop2= workshop1.get();
	                workshop2.setFirstName(workshop2.getFirstName());
	                workshop2.setTitle(workshop2.getTitle());
	                workshop2.setUserRole(workshop2.getUserRole());
	                workshop2.setTime(dateTime.getTime());
	                workshop2.setPassword(workshop2.getPassword());
	                workshop2.setLastName(workshop2.getLastName());
	                workshop2.setFileUrl(workshop2.getFileUrl());
	                workshop2.setDescription(workshop2.getDescription());
	                workshop2.setDate(dateTime.getDate());
	                workshop2.setCountry(workshop2.getCountry());
	                workshop2.setEmail(workshop2.getEmail());
	                workshop2.setIsApproved(true);
	              
	                
	                String email = workshop2.getEmail();

	                workshopRepository.save(workshop2);
	                sendEmail(workshop2, email);
	                return workshop2;
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("error getting update workshop " + e);
	        }
    	
    	
    }







}
