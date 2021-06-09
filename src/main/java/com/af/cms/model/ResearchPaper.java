package com.af.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "ResearchPaper")
public class ResearchPaper {
	
	
	 @Transient

	 public static final String SEQUENCE_NAME = "research_paper_sequence";
	
	 
		@Id
		@Indexed(unique = true)
		private int id;
		@Field(name = "email")
		private String email;
		@Field(name = "user_id")
		private String userId;
		@Field(name = "contact_number")
		private String contactNumber;
		@Field(name = "affiliation")
		private String affiliation ;
		@Field(name = "research_title")
		private String title;
		@Field(name = "description")
		private String description;
		@Field(name = "isApproved")
		private Boolean isApproved;
		@Field(name = "pdf_url")
		private String fileurl;
			
		
		public ResearchPaper() {
			super();
	
		}


		public ResearchPaper(int id, String email, String userId, String contactNumber, String affiliation,
				String title, String description, Boolean isApproved, String fileurl) {
			super();
			this.id = id;
			this.email = email;
			this.userId = userId;
			this.contactNumber = contactNumber;
			this.affiliation = affiliation;
			this.title = title;
			this.description = description;
			this.isApproved = isApproved;
			this.fileurl = fileurl;
			
			
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getUserId() {
			return userId;
		}


		public void setUserId(String userId) {
			this.userId = userId;
		}


		public String getContactNumber() {
			return contactNumber;
		}


		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}


		public String getAffiliation() {
			return affiliation;
		}


		public void setAffiliation(String affiliation) {
			this.affiliation = affiliation;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public Boolean getIsApproved() {
			return isApproved;
		}


		public void setIsApproved(Boolean isApproved) {
			this.isApproved = isApproved;
		}


		public String getFileurl() {
			return fileurl;
		}


		public void setFileurl(String fileurl) {
			this.fileurl = fileurl;
		}


		@Override
		public String toString() {
			return "ResearchPaper [id=" + id + ", email=" + email + ", userId=" + userId + ", contactNumber="
					+ contactNumber + ", affiliation=" + affiliation + ", title=" + title + ", description="
					+ description + ", isApproved=" + isApproved + ", fileurl=" + fileurl + "]";
		}
		
		

}

