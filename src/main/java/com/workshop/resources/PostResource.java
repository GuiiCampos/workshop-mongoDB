package com.workshop.resources;

import com.workshop.domain.Post;
import com.workshop.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);

        return ResponseEntity.ok().body(post);
    }
}
