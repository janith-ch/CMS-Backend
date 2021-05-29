package com.af.cms.model;


import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String userRole;
    private String requestUserRole;
    private String password;
    private String country;
}

