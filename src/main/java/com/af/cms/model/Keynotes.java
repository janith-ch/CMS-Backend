package com.af.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
@Data
@Document(collection = "keynotes")
public class Keynotes {

	 
		@Id
		private String id;
		@Field(name = "name")
		private String name ;
		@Field(name = "affiliation")
		private String affiliation;
		@Field(name = "email")
		private String email;
		@Field(name = "bio")
		private String bio;
		@Field(name = "image_url")
		private String imageUrl;

			
		
		
		
		
		


}
