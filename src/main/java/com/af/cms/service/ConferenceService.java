package com.af.cms.service;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.af.cms.model.Conference;
import com.af.cms.repository.ConferenceRepository;

@Service
public class ConferenceService {
	
	private static final Logger log = LoggerFactory.getLogger(ConferenceService.class);

	
	@Autowired
	private SequenceGeneratorService service;

	
	@Autowired
	private ConferenceRepository conferenceRepository;
	


	public Conference saveConference(Conference conference) throws IOException {

		try {
			 
			conference.setId(service.getSequenceNumber(Conference.SEQUENCE_NAME));
			return conferenceRepository.insert(conference);

		}catch (Exception e) {
			log.debug("Insert error:" + e);
			return null;
		} 
	}


	public List<Conference> getAllConference() { 

		return conferenceRepository.findAll(); 
	}


	public int deleteConference(int id) {

		try {
			conferenceRepository.deleteById(id);
			return 1;
		}catch (Exception e) {
			log.info("Delete error: "+ e);
			return 0;
		}

	}


	public int updateConference(Conference conference) {

		try {
			int id = conference.getId();
			Iterable<Conference>  object  = conferenceRepository.findAllById(Collections.singleton(id));

			if (object == null) {
                return 99;
            } else {
                conferenceRepository.save(conference);
                return 1;
            }

		}catch (Exception e) {
			log.info("error" + e);
			return 0;
		}
	}


	public Conference getConferenceById(int id) {
		
	  return conferenceRepository.findById(id).get();
	}


}
