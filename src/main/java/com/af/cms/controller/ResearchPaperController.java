package com.af.cms.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.af.cms.model.ResearchPaper;
import com.af.cms.respone.CommonResponse;

import com.af.cms.service.ResearchPaperService;
import com.af.cms.service.ResearchStorageService;


@Controller
public class ResearchPaperController {
	
	@Autowired
	public ResearchPaperService researchPaperService;
	
	@Autowired
	public ResearchStorageService researchStorageService;
	

	@PostMapping("/researchPaper")
	 public ResponseEntity<?> requestResearchPaper(@RequestBody ResearchPaper researchPaper) {
		
		    
		
    		ResearchPaper respone = researchPaperService.saveResearchPaper(researchPaper);
   
    		if(respone.equals(null)) {
    		
    			return ResponseEntity.ok(new CommonResponse<ResearchPaper>(false,null,respone));
    		}else {
    		
    			return ResponseEntity.ok(new CommonResponse<ResearchPaper>(true, null,respone));
    		}
			}
	
	
	@GetMapping("/researchPapers")
	public ResponseEntity<?> getAllResearchPaper() {

		
	return ResponseEntity.ok(new CommonResponse<List<ResearchPaper>>(true,null,researchPaperService.getAllWorkshop()));
	
	}
	
	@DeleteMapping("/researchPaper/{id}")
	public ResponseEntity<?> deleteResearchPaperById(@PathVariable String id) {
        int result = researchPaperService.deleteWorkshop(id);
        
        if(result == 1) {
        	return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
        }else {
        	
        	return ResponseEntity.ok(new CommonResponse<Integer>(false,null,result));
        }
		
	}
	
	@PutMapping("/researchPaper/approve/{id}")
	public ResponseEntity<?> updateResearchPaperIsApprovedStatus(@PathVariable String id){
		
		int result = researchPaperService.updateApprovedStatus(id);
		
		if(result == 1) {
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
			
		}else {
			
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
			
		}
			
	}
	
	
	
	@GetMapping("/researchPaper/{id}")
	public ResponseEntity<?> getByresearchPaperID(@PathVariable String id){

		
		ResearchPaper researchPaper = researchPaperService.getResearchPaperbyId(id);
		
		return ResponseEntity.ok(new CommonResponse<ResearchPaper>(true,null,researchPaper));
		
		
		
	}
	
	@PostMapping("/fileUpload")
    public ResponseEntity<?> uploadResearchPaper(@RequestParam("paper") MultipartFile file) {
        try {
		
		String fileName = researchStorageService.storeResearchPaper(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/research/")
                .path(fileName)
                .toUriString();
        
        System.out.println(fileDownloadUri);
        
        return ResponseEntity.ok(new CommonResponse<String>(true,null,fileDownloadUri));
        
       }
        catch (Exception e) {
        	System.out.println(e);
        	return ResponseEntity.ok(new CommonResponse<String>(false,null,null));		}

        
    }
	
	@GetMapping("/research/{fileName:.+}")
    public ResponseEntity<Resource> downloadResearch(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = researchStorageService.loadResearchAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	@PutMapping("researchPaper/update/{id}")
	public ResponseEntity<?> editWorkshop(@PathVariable String id, @RequestBody ResearchPaper researchPaper) {

	 ResearchPaper respone = researchPaperService.updateResearchPaper(researchPaper, id);

	 if(respone == null) {

	 return ResponseEntity.ok(new CommonResponse<ResearchPaper>(false,null,respone));

	 }else {
	return ResponseEntity.ok(new CommonResponse<ResearchPaper>(true,null,respone));
	}
	 }

	@GetMapping("/researchPaper/approvedList")
	public ResponseEntity<?> getApprovedPaperList(){
	return ResponseEntity.ok(new CommonResponse<List<ResearchPaper>>(true,null,researchPaperService.getAllApprovedResearchPapers()));

	 }
	

}
