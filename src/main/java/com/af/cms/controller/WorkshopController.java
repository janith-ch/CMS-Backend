package com.af.cms.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.af.cms.dto.DateTime;
import com.af.cms.model.Workshop;
import com.af.cms.service.FileStorageService;
import com.af.cms.service.WorkshopService;
import com.af.cms.respone.CommonResponse;
import com.af.cms.respone.UploadFileResponse;


@RestController
@CrossOrigin
public class WorkshopController {
	
	@Autowired
	public WorkshopService WorkshopService;
	
	@Autowired
	public FileStorageService fileStorageService;
	
	
	@PostMapping("/workshop")
	 public ResponseEntity<?> requestWorkshop(@RequestBody Workshop workshop) throws IOException {
		
      
    		Workshop respone = WorkshopService.saveWorkshop(workshop);
      
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
	
//	@PutMapping("workshop/approve/{id}")
//	public ResponseEntity<?> updateIsApprovedStatus(@PathVariable int id){
//		
//		int result = WorkshopService.updateApprovedStatus(id);
//		
//		if(result == 1) {
//			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
//			
//		}else {
//			
//			return ResponseEntity.ok(new CommonResponse<Integer>(true,null,result));
//			
//		}
//			
//	}
//	
	
	@GetMapping("/workshop/{id}")
	public ResponseEntity<?> getWorkshopByID(@PathVariable int id){
		
		Workshop workshop = WorkshopService.getWorkshopByid(id);
		
		return ResponseEntity.ok(new CommonResponse<Workshop>(true,null,workshop));
		
		
	}

	
	@PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }
	
	
	 @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

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
	 
	 
	 
	 
	 @PutMapping("workshop/update/{id}")
		public  ResponseEntity<?> editWorkshop(@PathVariable Integer id, @RequestBody Workshop workshop) {

			Workshop respone =  WorkshopService.updateWorkshop(workshop, id);

			if(respone == null) {

				return ResponseEntity.ok(new CommonResponse<Workshop>(false,null,respone));

			}else {
				return ResponseEntity.ok(new CommonResponse<Workshop>(true,null,respone));
			}
		}

	 
	 
	 @GetMapping("/workshop/approvedList")
		public  ResponseEntity<?> getApprovedList(){
			
			return ResponseEntity.ok(new CommonResponse<List<Workshop>>(true,null,WorkshopService.getAllApprovedWorkshop()));

		}
	 
	 
	 @PutMapping("workshop/approve/{id}")
		public ResponseEntity<?> updateIsApprovedStatus(@PathVariable int id, @RequestBody DateTime dateTime){
			
			Workshop result = WorkshopService.approveResearchpaper(id,dateTime);
			
			if(result == null) {
				return ResponseEntity.ok(new CommonResponse<Workshop>(false,null,result));
				
			}else {
				
				return ResponseEntity.ok(new CommonResponse<Workshop>(true,null,result));
				
			}
				
		}
	
	
	
	
}
