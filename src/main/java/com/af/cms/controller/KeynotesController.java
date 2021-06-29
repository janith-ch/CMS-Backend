package com.af.cms.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.af.cms.model.Keynotes;
import com.af.cms.respone.CommonResponse;
import com.af.cms.respone.ImageRespone;
import com.af.cms.service.KeynotesService;


@RestController
public class KeynotesController {

	private static final Logger log = LoggerFactory.getLogger(KeynotesController.class);

	@Autowired
	private KeynotesService keynotesService;



	@PostMapping("/keynote")
	public ResponseEntity<?> createKeynotes(@RequestBody  Keynotes keynotes) throws IOException {



		Keynotes respone = keynotesService.saveKeynote(keynotes);



		if(respone.equals(null)) {
			return ResponseEntity.ok(new CommonResponse<Keynotes>(false,null,respone));
		}

		return ResponseEntity.ok(new CommonResponse<Keynotes>(true, null,respone));

	}


	@PostMapping("/keynote/profileImage")
	public ResponseEntity<?> updateProfileImage(@RequestPart("file") MultipartFile file) throws IOException {

		ImageRespone result = keynotesService.updateImageurl(file);

		if(result == null) {

			return ResponseEntity.ok(new CommonResponse<ImageRespone>(false,"Image Upload Fail",result));


		}else {
			return ResponseEntity.ok(new CommonResponse<ImageRespone>(true,null,result)); 


		}	

	}

	@DeleteMapping("/keynote/delete/{id}")
	public ResponseEntity<?> deletekeynoteById(@PathVariable Integer id) {
		int result = keynotesService.keynotedeleteById(id);

		if(result == 1) {
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
		}else {

			return ResponseEntity.ok(new CommonResponse<Integer>(false,null,result));
		}

	}

	@GetMapping("/keynote/{conferenceId}")
	public ResponseEntity<?> getkeynotesByConfernceId(@PathVariable Integer conferenceId) {

		try {
			return  ResponseEntity.ok(new CommonResponse <List<Keynotes>>(true,null,keynotesService.getKeynoteByConferenceID(conferenceId)));

		}catch(Exception e) {
			return  ResponseEntity.ok(new CommonResponse <List<Keynotes>>(false,"keynotes not found",null));
		}

	}


	@PutMapping("keynote/update/{id}")
	public  ResponseEntity<?> editKeynotesByID(@PathVariable Integer id, @RequestBody Keynotes keynotes) {

		Keynotes respone =  keynotesService.updateKeynotes(keynotes, id);

		if(respone == null) {

			return ResponseEntity.ok(new CommonResponse<Keynotes>(false,null,respone));

		}else {
			return ResponseEntity.ok(new CommonResponse<Keynotes>(true,null,respone));
		}
	}

	@GetMapping("/keynote/list")
	public  ResponseEntity<?> getAllKeynotes(){
		
		return ResponseEntity.ok(new CommonResponse<List<Keynotes>>(true,null,keynotesService.getAllKeynotes()));

	}
	
	
	@GetMapping("/keynote/id/{id}")
	public Optional<Keynotes> getkeynotesById(@PathVariable Integer id) {

		try {
			return keynotesService.getkeynoteById(id);
		}catch(Exception e) {
			log.info("error" + e);
			return null;
		}

	}

}
