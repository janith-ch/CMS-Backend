package com.af.cms.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "workshop")
public class Workshop {
	
	
	 @Transient
	 public static final String SEQUENCE_NAME = "user_sequence";
	
	@Id
	@Indexed(unique = true)
	private int id;
	@Field(name = "conductor_name")
	private String name;
	@Field(name = "user_id")
	private String userId;
	@Field(name = "conductor_email")
	private String email;
	@Field(name = "contact_number")
	private String contactNumber;
	@Field(name = "affiliation")
	private String affiliation ;
	@Field(name = "workshop_title")
	private String title;
	@Field(name = "description")
	private String description;
	@Field(name = "proposal")
	private Binary file;
	@Field(name = "is_approved")
	private Boolean isApproved;

	
	
	public Workshop() {
		
	}
	



	public Workshop(int id, String name, String userId, String email, String contactNumber, String affiliation,
			String title, String description, Binary file, Boolean isApproved) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.email = email;
		this.contactNumber = contactNumber;
		this.affiliation = affiliation;
		this.title = title;
		this.description = description;
		this.file = file;
		this.isApproved = isApproved;
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
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



	public Binary getFile() {
		return file;
	}



	public void setFile(Binary file) {
		this.file = file;
	}



	public String getAffiliation() {
		return affiliation;
	}



	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}
	
	

	public Boolean getIsApproved() {
		return isApproved;
	}


	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}


	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	@Override
	public String toString() {
		return "Workshop [id=" + id + ", name=" + name + ", userId=" + userId + ", email=" + email + ", contactNumber="
				+ contactNumber + ", affiliation=" + affiliation + ", title=" + title + ", description=" + description
				+ ", file=" + file + ", isApproved=" + isApproved + "]";
	}




	

	
	







	





	
	

}
