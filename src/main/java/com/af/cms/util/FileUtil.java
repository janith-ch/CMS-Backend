package com.af.cms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.af.cms.exception.FileStorageException;
import com.af.cms.respone.ImageRespone;



public class FileUtil {
	
	static final boolean isPosix =
		    FileSystems.getDefault().supportedFileAttributeViews().contains("posix");
	
	
	
	  public static ImageRespone imageupload(MultipartFile file) {
	        // Normalize file name
		  
		  	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); 
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        String gerneratedFileName = timeStamp+"-"+fileName;
	        String fileDownloadUrl  = "http://localhost/CMS/keynotes/"+gerneratedFileName;
	        
	        Set<PosixFilePermission> perms = new HashSet<>();
	        perms.add(PosixFilePermission.OWNER_READ);
	        perms.add(PosixFilePermission.OWNER_WRITE);
	        perms.add(PosixFilePermission.OWNER_EXECUTE);
	        perms.add(PosixFilePermission.GROUP_READ);
	        perms.add(PosixFilePermission.OTHERS_READ);
	     
            createFoldersIfNeeded("K");

	        try {
	            // Check if the file's name contains invalid characters
	            if(gerneratedFileName.contains("..")) {
	                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + gerneratedFileName);
	            }
	             
//             Path targetLocation = Paths.get(System.getProperty("user.home")+File.separator+"CMS"+File.separator+"workshop").resolve(gerneratedFileName);;
	            Path targetLocation = Paths.get("C:"+File.separator+"xampp"+File.separator+"htdocs"+File.separator+"CMS"+File.separator+"keynotes").resolve(gerneratedFileName);;   
	            System.out.println(targetLocation);

	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	            if(FileUtil.isPosix) {
	            	 Files.setPosixFilePermissions(targetLocation, perms);
	            }
	             ImageRespone imageRespone = new ImageRespone();
	             
	             imageRespone.setImageName(gerneratedFileName);
	             imageRespone.setImageUrl(fileDownloadUrl);
	            
	            return imageRespone;
	        } catch (IOException ex) {
	            throw new FileStorageException("Could not store file " + fileDownloadUrl + ". Please try again!", ex);
	        }
	    }
	  
	  
	  public static void createFoldersIfNeeded(String flag) {
		  
		   try {
			   
			   if(flag == "K") {
				   Files.createDirectories( Paths.get("C:"+File.separator+"xampp"+File.separator+"htdocs"+File.separator+"CMS"+File.separator+"keynotes"));
			   }else {
				     Files.createDirectories( Paths.get("C:"+File.separator+"xampp"+File.separator+"htdocs"+File.separator+"CMS"+File.separator+"ResearchPaper")); 
			   }
	          
	       
	           
	        } catch (Exception ex) {
	            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	        }
	  }

}
