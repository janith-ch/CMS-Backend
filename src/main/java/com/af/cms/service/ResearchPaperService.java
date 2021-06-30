package com.af.cms.service;
import java.util.List;
import java.util.Optional;

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


import com.af.cms.model.ResearchPaper;
import com.af.cms.model.Workshop;
import com.af.cms.repository.ResearchPaperRepository;


@Service
public class ResearchPaperService {
	
	private static final Logger log = LoggerFactory.getLogger(WorkshopService.class);

	@Autowired
	public ResearchPaperRepository researchPaperRepository; 

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${email.username}")
	private String sendFromEmail;


	public ResearchPaper saveResearchPaper(ResearchPaper researchPaper) {
                   

		try {
			
			researchPaper.setIsApproved(false);			
			return researchPaperRepository.insert(researchPaper);

		}catch (Exception e) {
			log.info("Insert error:" + e);
			return null;
		} 
	}


	public List<ResearchPaper> getAllWorkshop() { 

		return researchPaperRepository.findAll(); 
	}


	public int deleteWorkshop(String id) {

		try {
			researchPaperRepository.deleteById(id);
			return 1;
		}catch (Exception e) {
			log.info("Delete error: "+ e);
			return 0;
		}

	}


	public int updateApprovedStatus(String id) {

		try {
			ResearchPaper paper = new ResearchPaper();

			paper = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), ResearchPaper.class);
			paper.setIsApproved(true);
			String email = paper.getEmail();
			mongoTemplate.save(paper,"ResearchPaper");
			sendEmail(paper,email);
			return 1;


		}catch (Exception e) {
			log.info("error" + e);
			return 0;
		}
	}


	public ResearchPaper getResearchPaperbyId(String id) {
		
		try {
			  
		ResearchPaper paper= researchPaperRepository.findById(id).get();
		
		return paper;
		
		}catch (Exception e) {
			return null;
		}
	}
	
	
	
	public ResearchPaper updateResearchPaper(ResearchPaper researchPaper,String id) {

		 try {
		Optional<ResearchPaper> paper = researchPaperRepository.findById(id);
		if (paper == null) {
		return null;
		} else {
		ResearchPaper paper2= paper.get();
		paper2.setUserType(researchPaper.getUserType());
		paper2.setTitle(paper2.getTitle());
		paper2.setPassword(researchPaper.getPassword());
		paper2.setLastName(researchPaper.getLastName());
		paper2.setIsApproved(paper2.getIsApproved());
		paper2.setFirstName(researchPaper.getFirstName());
		paper2.setFileurl(paper2.getFileurl());
		paper2.setEmail(researchPaper.getEmail());
		paper2.setDescription(paper2.getDescription());
		paper2.setCountry(paper2.getCountry());
		paper2.setAffiliation(paper2.getAffiliation());


		 researchPaperRepository.save(paper2);
		return paper2;
		}
		} catch (Exception e) {
			log.info("Error" + e);
			return null;
		}
		}

	public List<ResearchPaper> getAllApprovedResearchPapers(){
		try {

		 List<ResearchPaper> list = mongoTemplate.find(Query.query(Criteria.where("is_approved").is(true)), ResearchPaper.class);
		return list;

		}catch (Exception e) {
		log.info("error" + e);
		return null;
		}
		}
	
public void sendEmail(ResearchPaper researchPaper,String email) {
		
		String name = researchPaper.getLastName();


		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		msg.setFrom(sendFromEmail);

		msg.setSubject("Email of Approval for researcher to present the research paper at the ICAF 2021.");
		msg.setText( "Dear " + name + System.lineSeparator()+  System.lineSeparator()+   "We are pleased to inform you that your research paper, “ How to deal with procrastination” has approved for present at the ICAF 2021."
				+ " Please make sure to proceed the payment for the presentation." 
				+ System.lineSeparator()+  System.lineSeparator()+"Thank you !");
		javaMailSender.send(msg);

	}

}
