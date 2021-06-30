package com.af.cms.dto;

import lombok.Data;

@Data
public class Authenticate {
	
	private String email;
	private String userRole;
	private String message;

}
