package com.af.cms.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
@Data
@Document(collection = "workshop")
public class Workshop {
	
	
	 @Transient
	 public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	@Indexed(unique = true)
	private int id;
	@Field(name = "first_name")
	private String firstName;
	@Field(name = "last_name")
	private String lastName;
	@Field(name = "password")
	private String password;
	@Field(name = "country")
	private String country;
	@Field(name = "user_id")
	private String userRole;
	@Field(name = "email")
	private String email;
	@Field(name = "date")
	private String date;
	@Field(name = "time")
	private String time;
	@Field(name = "workshop_title")
	private String title;
	@Field(name = "description")
	private String description;
	@Field(name = "proposal")
	private String fileUrl;
	@Field(name = "is_approved")
	private Boolean isApproved;
	
	
	
	@Override
	public String toString() {
		return "Workshop [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", speakerName="  + ", country=" + country + ", userId=" + userRole + ", email=" + email
				+ ", date=" + date + ", time=" + time + ", title=" + title + ", description=" + description + ", file="
				+ fileUrl + ", isApproved=" + isApproved + "]";
	}
	
	
	
	
	


	




	

	
	







	





	
	

}
