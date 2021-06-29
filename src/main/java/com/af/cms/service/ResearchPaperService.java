package com.af.cms.service;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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


	public int deleteWorkshop(int id) {

		try {
			researchPaperRepository.deleteById(id);
			return 1;
		}catch (Exception e) {
			log.info("Delete error: "+ e);
			return 0;
		}

	}


	public int updateApprovedStatus(int id) {

		try {
			ResearchPaper paper = new ResearchPaper();

			paper = mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), ResearchPaper.class);
			paper.setIsApproved(true);
			mongoTemplate.save(paper,"workshop");
			return 1;


		}catch (Exception e) {
			log.info("error" + e);
			return 0;
		}
	}


	public ResearchPaper getPdfByid(int id) {
		
	  return researchPaperRepository.findById(id).get();
	}



}
