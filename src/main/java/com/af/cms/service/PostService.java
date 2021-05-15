package com.af.cms.service;

import com.af.cms.model.Post;
import com.af.cms.repository.PostRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PostService implements PostServiceInt{
    @Autowired
    PostRepository postRepository;
    @Override
    public String createPost( String title,MultipartFile file) throws IOException {
        Post post = new Post();
        post.setImage(
                new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        post.setTitle(title);
        post = postRepository.insert(post);

        return post.getId();
    }
    @Override
    public Post getPhoto(String id) {
        return postRepository.findById(id).get();
    }
}
