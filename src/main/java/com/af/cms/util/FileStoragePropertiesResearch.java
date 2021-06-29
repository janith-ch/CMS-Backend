package com.af.cms.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "research")
public class FileStoragePropertiesResearch {
	    private String uploadDir;

	    public String getUploadDir() {
	        return uploadDir;
	    }

	    public void setUploadDir(String uploadDir) {
	        this.uploadDir = uploadDir;
	    }
	    
	    
	   }


