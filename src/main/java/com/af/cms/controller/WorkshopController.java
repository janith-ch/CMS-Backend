package com.af.cms.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.af.cms.model.Workshop;
import com.af.cms.service.WorkshopService;
import com.af.cms.respone.CommonResponse;


@RestController
public class WorkshopController {
	
	@Autowired
	public WorkshopService WorkshopService;
	
	
	@PostMapping("/workshop")
	 public ResponseEntity<?> requestWorkshop(@RequestPart("file") MultipartFile file,@RequestParam("name") String name,@RequestParam("userId") String userId,@RequestParam("email") String email
			 ,@RequestParam("contactNumber") String contactNumber,@RequestParam("affiliation") String affiliation,@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("isApproved") boolean isApproved) throws IOException {
		
      
    		Workshop respone = WorkshopService.saveWorkshop(file,name,userId,email,contactNumber,affiliation ,title,description,isApproved);
      
    		if(respone.equals(null)) {
    			return ResponseEntity.ok(new CommonResponse<Workshop>(false,null,respone));
    		}
    		
    			return ResponseEntity.ok(new CommonResponse<Workshop>(true, null,respone));
       
			}
	
	
	@GetMapping("/workshops")
	public ResponseEntity<?> getAllWorkshop() {
		
	return ResponseEntity.ok(new CommonResponse<List<Workshop>>(true,null,WorkshopService.getAllWorkshop()));
	
	}
	
	@DeleteMapping("/workshops/{id}")
	public ResponseEntity<?> deleteWorkshopById(@PathVariable int id) {
        int result = WorkshopService.deleteWorkshop(id);
        
        if(result == 1) {
        	return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
        }else {
        	
        	return ResponseEntity.ok(new CommonResponse<Integer>(false,null,result));
        }
		
	}
	
	@PutMapping("workshop/approve/{id}")
	public ResponseEntity<?> updateIsApprovedStatus(@PathVariable int id){
		
		int result = WorkshopService.updateApprovedStatus(id);
		
		if(result == 1) {
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
			
		}else {
			
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
			
		}
			
	}
	
	@GetMapping("workshop/pdf/{id}")
	public ResponseEntity<?> getPdfFilebyId(@PathVariable int id, Model model){
		
		
		Workshop workshop = WorkshopService.getPdfByid(id);
		model.addAttribute("file", Base64.getEncoder().encodeToString(workshop.getFile().getData()));
//		System.out.println("printbefore" + workshop.getFile());
//		byte[] decodedBytes = java.util.Base64.getEncoder().encode(workshop.getFile().getData());
//		System.out.println("print" + decodedBytes);
		return ResponseEntity.ok(new CommonResponse<String>(true,null,"Preview PDF..."));
		
		
		
	}
	
	
	@GetMapping("/workshop/{id}")
	public ResponseEntity<?> getWorkshopByID(@PathVariable int id){
		System.out.println("hi");
		
		Workshop workshop = WorkshopService.getPdfByid(id);
		
		return ResponseEntity.ok(new CommonResponse<Workshop>(true,null,workshop));
		
		
		
	}

}
