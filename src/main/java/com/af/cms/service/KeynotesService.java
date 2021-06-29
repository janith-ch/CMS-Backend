package com.af.cms.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.af.cms.model.Keynotes;
import com.af.cms.repository.KeynotesRepository;
import com.af.cms.respone.ImageRespone;
import com.af.cms.util.FileUtil;

@Service
public class KeynotesService {
	
	private static final Logger log = LoggerFactory.getLogger(KeynotesService.class);

	@Autowired
	private KeynotesRepository keynotesRepository;

	@Autowired
	public SequenceGeneratorService generatorService;
	
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public Keynotes saveKeynote(Keynotes keynotes) throws IOException {


		try {
			
			keynotes.setId(generatorService.getSequenceNumber(Keynotes.SEQUENCE_NAME));
			
			log.info("object" + keynotes);
			
			return keynotesRepository.insert(keynotes);


		}catch (Exception e) {
			log.debug("Insert error:" + e);
			return null;
		} 
	}
	
	public ImageRespone updateImageurl(MultipartFile file) throws IOException {


		ImageRespone imageRespone = FileUtil.imageupload(file);

		return imageRespone;
	}
	
	
	public Keynotes updateKeynotes(Keynotes keynotes, int id) {
		
		 try {
	            Optional<Keynotes> keynote = keynotesRepository.findById(id);
	            if (keynote == null) {
	                return null;
	            } else {
	              Keynotes keynote1= keynote.get();
	                keynote1.setName(keynotes.getName());
	                keynote1.setImageUrl(keynotes.getImageUrl());
	                keynote1.setImageName(keynotes.getImageName());
	                keynote1.setEmail(keynotes.getEmail());
	                keynote1.setAffiliation(keynotes.getAffiliation());
	                keynote1.setBio(keynotes.getBio());
	                keynote1.setConferenceId(keynotes.getConferenceId());
	                
	               

	                keynotesRepository.save(keynote1);
	                return keynote1;
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("error getting update user " + e);
	        }
	
		
	}
	
	
	public List<Keynotes> getKeynoteByConferenceID(int conferenceid){
		
		try {

			List<Keynotes> list  = mongoTemplate.find(Query.query(Criteria.where("conference_id").is(conferenceid)), Keynotes.class);
			return list;


		}catch (Exception e) {
			log.info("error" + e);
			return null;
		}
		
			}
	
	
	public int keynotedeleteById(int id) {
		
		try {
			keynotesRepository.deleteById(id);
			return 1;
		}catch (Exception e) {
			log.info("Delete error: "+ e);
			return 0;
		}
		
		
	}
	
	public List<Keynotes> getAllKeynotes(){
		
		return keynotesRepository.findAll();
	}
	
	
	public Optional<Keynotes> getkeynoteById(Integer id) {
		
		return keynotesRepository.findById(id);
	}



}
