package com.af.cms.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import com.af.cms.model.Keynotes;
import com.af.cms.repository.KeynotesRepository;
import com.af.cms.util.FileUtil;

@Service
public class KeynotesService {
	
	private static final Logger log = LoggerFactory.getLogger(KeynotesService.class);

	@Autowired
	private KeynotesRepository keynotesRepository;

	@Autowired
	public SequenceGeneratorService generatorService;
	
	


	public Keynotes saveKeynote(MultipartFile file,Integer conferenceId, String name, String affiliation,String contactNumber , String email, String bio) throws IOException {


		try {

			Keynotes keynotes = new Keynotes();

			keynotes.setId(generatorService.getSequenceNumber(Keynotes.SEQUENCE_NAME));

			String fileName = keynotes.getId()+"$"+file.getOriginalFilename();

			String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static/";

//			FileUtil.fileupload(file.getBytes(), path, fileName);

			keynotes.setName(name);
			keynotes.setContactNumber(contactNumber);
			keynotes.setEmail(email);
			keynotes.setAffiliation(affiliation);
			keynotes.setBio(bio);
			keynotes.setImageUrl("http://localhost:9090/"+fileName);
			keynotes.setConferenceId(conferenceId);
			
			log.info("object" + keynotes);
			
			return keynotesRepository.insert(keynotes);


		}catch (Exception e) {
			log.debug("Insert error:" + e);
			return null;
		} 
	}


}
