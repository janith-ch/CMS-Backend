package com.af.cms.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Conference")
public class Conference {


    @Id
    private String id;
    @Field(name = "heading")
    private String heading;
    @Field(name = "date")
    private String date;
    @Field(name = "time")
    private String time;
    @Field(name = "organizer")
    private String organizer;
    @Field(name = "venue")
    private String venue;
    @Field(name = "description")
    private String description;
    @Field(name = "image")
    private String image;
    @Field(name = "status")
    private String status;


}
