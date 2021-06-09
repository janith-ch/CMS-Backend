package com.af.cms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.af.cms.model.Conference;
import com.af.cms.respone.CommonResponse;
import com.af.cms.service.ConferenceService;

@RestController
public class ConferenceController {
	
	@Autowired
	public ConferenceService conferenceService;
	

	@PostMapping("/conference")
	 public ResponseEntity<?> CreateConference(@RequestBody Conference conference) throws IOException {
		
	
    		Conference respone = conferenceService.saveConference(conference);
      
    		if(respone.equals(null)) {
    			return ResponseEntity.ok(new CommonResponse<Conference>(false,null,respone));
    		}
    		
    			return ResponseEntity.ok(new CommonResponse<Conference>(true, null,respone));
       
			}
	
	
	@GetMapping("/conferences")
	public ResponseEntity<?> getAllConferences () {
		
	return ResponseEntity.ok(new CommonResponse<List<Conference>>(true,null,conferenceService.getAllConference()));
	
	}
	
	@DeleteMapping("/conferences/{id}")
	public ResponseEntity<?> deleteResearchPaperById(@PathVariable int id) {
        int result = conferenceService.deleteConference(id);
        
        if(result == 1) {
        	return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
        }else {
        	
        	return ResponseEntity.ok(new CommonResponse<Integer>(false,null,result));
        }
		
	}
	
	@PutMapping("/conference/update")
	public ResponseEntity<?> updateConference(@RequestBody Conference conference){
		
		int result = conferenceService.updateConference(conference);
		
		if(result == 1) {
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
			
		}else{
			
			return ResponseEntity.ok(new CommonResponse<Integer>(false,null,result));
			
		}
			
	}
	
	
	@GetMapping("/conference/{id}")
	public ResponseEntity<?> getConferenceById(@PathVariable int id){

		
		Conference conference = conferenceService.getConferenceById(id);
		
		return ResponseEntity.ok(new CommonResponse<Conference>(true,null,conference));
		
		
		
	}

	

}
