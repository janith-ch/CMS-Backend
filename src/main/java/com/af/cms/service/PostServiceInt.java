package com.af.cms.service;

import com.af.cms.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostServiceInt {

    public String createPost( String title,MultipartFile file) throws IOException;
    public Post getPhoto(String id);
}
