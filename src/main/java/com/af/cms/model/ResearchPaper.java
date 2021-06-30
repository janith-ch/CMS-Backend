package com.af.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "ResearchPaper")
public class ResearchPaper {
	
	 
		@Id
		@Indexed(unique = true)
		private String id;
		@Field(name = "author_firstName")
		private String firstName;
		@Field(name = "author_last_name")
		private String lastName;
		@Field(name = "password")
		private String password;
		@Field(name = "country")
		private String country;
		@Field(name = "email")
		private String email;
		@Field(name = "user_type")
		private String userRole;
		@Field(name = "affiliation")
		private String affiliation ;
		@Field(name = "research_title")
		private String title;
		@Field(name = "description")
		private String description;
		@Field(name = "is_approved")
		private Boolean isApproved;
		@Field(name = "pdf_url")
		private String fileurl;
		
			
		

}

