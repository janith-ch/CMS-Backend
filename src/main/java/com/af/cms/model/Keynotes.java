package com.af.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "keynotes")
public class Keynotes {
	
	
	 @Transient
	 public static final String SEQUENCE_NAME = "keynotes";
	
	 
		@Id
		@Indexed(unique = true)
		private int id;
		@Field(name = "conference_id")
		private int conferenceId;
		@Field(name = "name")
		private String name ;
		@Field(name = "affiliation")
		private String affiliation;
		@Field(name = "contact_number")
		private String contactNumber;
		@Field(name = "email")
		private String email;
		@Field(name = "bio")
		private String bio;
		@Field(name = "image_url")
		private String imageUrl;

			
		
		
		public Keynotes() {
		
		}




		public Keynotes(int id, int conferenceId, String name, String affiliation, String contactNumber,
				String email, String bio, String imageUrl) {
			super();
			this.id = id;
			this.conferenceId = conferenceId;
			this.name = name;
			this.affiliation = affiliation;
			this.contactNumber = contactNumber;
			this.email = email;
			this.bio = bio;
			this.imageUrl = imageUrl;
		}







		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public int getConferenceId() {
			return conferenceId;
		}



		public void setConferenceId(int conferenceId) {
			this.conferenceId = conferenceId;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getAffiliation() {
			return affiliation;
		}



		public void setAffiliation(String affiliation) {
			this.affiliation = affiliation;
		}



		public String getContactNumber() {
			return contactNumber;
		}



		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getBio() {
			return bio;
		}



		public void setBio(String bio) {
			this.bio = bio;
		}
		
		



		public String getImageUrl() {
			return imageUrl;
		}




		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}




		@Override
		public String toString() {
			return "Keynotes [id=" + id + ", conferenceId=" + conferenceId + ", name=" + name + ", affiliation="
					+ affiliation + ", contactNumber=" + contactNumber + ", email=" + email + ", bio=" + bio
					+ ", imageUrl=" + imageUrl + "]";
		}
		
		
		
		


}
