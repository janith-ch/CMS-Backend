package com.af.cms.controller;



import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import com.af.cms.model.ResearchPaper;
import com.af.cms.respone.CommonResponse;
import com.af.cms.service.ResearchPaperService;

@Controller
public class ResearchPaperController {
	
	@Autowired
	public ResearchPaperService researchPaperService;
	

	@PostMapping("/researchPaper")
	 public ResponseEntity<?> requestResearchPaper(@RequestPart("file") MultipartFile file,@RequestParam("email") String email,@RequestParam("userId") String userId
			 ,@RequestParam("contactNumber") String contactNumber,@RequestParam("affiliation") String affiliation,@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("isApproved") boolean isApproved) throws IOException {
		
	
    		ResearchPaper respone = researchPaperService.saveResearchPaper(file,userId,email,contactNumber,affiliation ,title,description,isApproved);
      
    		if(respone.equals(null)) {
    			return ResponseEntity.ok(new CommonResponse<ResearchPaper>(false,null,respone));
    		}
    		
    			return ResponseEntity.ok(new CommonResponse<ResearchPaper>(true, null,respone));
       
			}
	
	
	@GetMapping("/researchPapers")
	public ResponseEntity<?> getAllResearchPaper() {
		
	return ResponseEntity.ok(new CommonResponse<List<ResearchPaper>>(true,null,researchPaperService.getAllWorkshop()));
	
	}
	
	@DeleteMapping("/researchPaper/{id}")
	public ResponseEntity<?> deleteResearchPaperById(@PathVariable int id) {
        int result = researchPaperService.deleteWorkshop(id);
        
        if(result == 1) {
        	return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
        }else {
        	
        	return ResponseEntity.ok(new CommonResponse<Integer>(false,null,result));
        }
		
	}
	
	@PutMapping("/researchPaper/approved/{id}")
	public ResponseEntity<?> updateResearchPaperIsApprovedStatus(@PathVariable int id){
		
		int result = researchPaperService.updateApprovedStatus(id);
		
		if(result == 1) {
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
			
		}else {
			
			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
			
		}
			
	}
	
	@GetMapping("/researchPaper/pdf/{id}")
	public ResponseEntity<?> getResearchPaperPdfFilebyId(@PathVariable int id, Model model){
		
		
		ResearchPaper researchPaper = researchPaperService.getPdfByid(id);
		
		String researchPdfUrl = researchPaper.getFileurl();
		
		return ResponseEntity.ok(new CommonResponse<String>(true,null,researchPdfUrl));
		
		
		
	}
	
	
	@GetMapping("/researchPaper/{id}")
	public ResponseEntity<?> getByresearchPaperID(@PathVariable int id){

		
		ResearchPaper researchPaper = researchPaperService.getPdfByid(id);
		
		return ResponseEntity.ok(new CommonResponse<ResearchPaper>(true,null,researchPaper));
		
		
		
	}

	
	

	
	
	
	
}
