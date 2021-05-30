package com.af.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Conference {
	
	 @Transient
	 public static final String SEQUENCE_NAME = "Conference";
	
	 
		@Id
		@Indexed(unique = true)
		private int id;
		@Field(name = "title")
		private String title;
		@Field(name = "email")
		private String email;
		@Field(name = "contact_number")
		private String contactNumber;
		@Field(name = "organizer")
		private String organizer ;
		@Field(name = "venue")
		private String venue;
		@Field(name = "description")
		private String description;
		@Field(name = "start_date")
		private String startDate;
		@Field(name = "end_date")
		private String endate;
		
		
		
		public Conference() {
			
		}



		public Conference(int id, String title, String email, String contactNumber, String organizer, String venue,
				String description, String startDate, String endate) {
			super();
			this.id = id;
			this.title = title;
			this.email = email;
			this.contactNumber = contactNumber;
			this.organizer = organizer;
			this.venue = venue;
			this.description = description;
			this.startDate = startDate;
			this.endate = endate;
		}



		public int getId() {
			return id;
		}



		public void setId(int id) {
			this.id = id;
		}



		public String getTitle() {
			return title;
		}



		public void setTitle(String title) {
			this.title = title;
		}



		public String getEmail() {
			return email;
		}



		public void setEmail(String email) {
			this.email = email;
		}



		public String getContactNumber() {
			return contactNumber;
		}



		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}



		public String getOrganizer() {
			return organizer;
		}



		public void setOrganizer(String organizer) {
			this.organizer = organizer;
		}



		public String getVenue() {
			return venue;
		}



		public void setVenue(String venue) {
			this.venue = venue;
		}



		public String getDescription() {
			return description;
		}



		public void setDescription(String description) {
			this.description = description;
		}



		public String getStartDate() {
			return startDate;
		}



		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}



		public String getEndate() {
			return endate;
		}



		public void setEndate(String endate) {
			this.endate = endate;
		}



		@Override
		public String toString() {
			return "Conference [id=" + id + ", title=" + title + ", email=" + email + ", contactNumber=" + contactNumber
					+ ", organizer=" + organizer + ", venue=" + venue + ", description=" + description + ", startDate="
					+ startDate + ", endate=" + endate + "]";
		}
		
		
		
		
		
		
		
		
		
			

}
