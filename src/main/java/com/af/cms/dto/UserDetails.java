package com.af.cms.dto;

import lombok.Data;

@Data
public class UserDetails {
	
	private String email;
	private String firstName;
	private String lastName;
	private String userRole;
	private String password;
	private String country;
	private String userID;

}
