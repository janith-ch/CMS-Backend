package com.af.cms.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.af.cms.model.Keynotes;
import com.af.cms.respone.CommonResponse;
import com.af.cms.service.KeynotesService;

@RestController
public class KeynotesController {
	
	private static final Logger log = LoggerFactory.getLogger(KeynotesController.class);
	
	@Autowired
	private KeynotesService keynotesService;
	
	
	
	@PostMapping("/keynote")
	 public ResponseEntity<?> createKeynotes(@RequestPart("file") MultipartFile file,@RequestParam("email") String email,@RequestParam("conferenceID") Integer conferenceId
			 ,@RequestParam("contactNumber") String contactNumber,@RequestParam("affiliation") String affiliation,@RequestParam("name") String name,@RequestParam("bio") String bio) throws IOException {
		
	
		
   		Keynotes respone = keynotesService.saveKeynote(file,conferenceId,name,affiliation,contactNumber,email,bio);
   		
   		
     
   		if(respone.equals(null)) {
   			return ResponseEntity.ok(new CommonResponse<Keynotes>(false,null,respone));
   		}
   		
   			return ResponseEntity.ok(new CommonResponse<Keynotes>(true, null,respone));
      
			}
	

}
