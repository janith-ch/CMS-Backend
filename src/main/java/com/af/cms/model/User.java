package com.af.cms.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;


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

