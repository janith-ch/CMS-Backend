package com.af.cms.service;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import com.af.cms.model.ResearchPaper;
import com.af.cms.repository.ResearchPaperRepository;


@Service
public class ResearchPaperService {
	
	private static final Logger log = LoggerFactory.getLogger(WorkshopService.class);

	@Autowired
	public ResearchPaperRepository researchPaperRepository; 

	@Autowired
	private MongoTemplate mongoTemplate;

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
			mongoTemplate.save(paper,"ResearchPaper");
			return 1;


		}catch (Exception e) {
			log.info("error" + e);
			return 0;
		}
	}


	public ResearchPaper getPdfByid(String id) {
		
	  return researchPaperRepository.findById(id).get();
	}
	
	
	
	public ResearchPaper updateResearchPaper(ResearchPaper researchPaper,String id) {

		 try {
		Optional<ResearchPaper> paper = researchPaperRepository.findById(id);
		if (paper == null) {
		return null;
		} else {
		ResearchPaper paper2= paper.get();
		paper2.setUserType(researchPaper.getUserType());
		paper2.setTitle(researchPaper.getTitle());
		paper2.setPassword(researchPaper.getPassword());
		paper2.setLastName(researchPaper.getLastName());
		paper2.setIsApproved(researchPaper.getIsApproved());
		paper2.setFirstName(researchPaper.getFirstName());
		paper2.setFileurl(researchPaper.getFileurl());
		paper2.setEmail(researchPaper.getEmail());
		paper2.setDescription(researchPaper.getDescription());
		paper2.setCountry(researchPaper.getCountry());
		paper2.setAffiliation(researchPaper.getAffiliation());


		 researchPaperRepository.save(paper2);
		return paper2;
		}
		} catch (Exception e) {
		throw new RuntimeException("error getting update workshops " + e);
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

}
