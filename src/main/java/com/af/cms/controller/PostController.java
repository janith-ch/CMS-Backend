package com.af.cms.controller;

import com.af.cms.model.Post;
import com.af.cms.service.PostServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostServiceInt postServiceInt;

    @PostMapping("/")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        String id = postServiceInt.createPost(title, image);
        return "redirect:/photos/" + id;
    }
    @GetMapping("/{id}")
    public Post getPhoto(@PathVariable String id, Model model) {
        Post photo = postServiceInt.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return photo;
    }
}
