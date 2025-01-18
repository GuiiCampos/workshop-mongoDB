package com.workshop.resources;

import com.workshop.domain.Post;
import com.workshop.resources.util.URL;
import com.workshop.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = " ") String txt) {
        txt = URL.decodeParam(txt);
        List<Post> posts = postService.findByTitle(txt);

        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = " ") String txt,
                                                 @RequestParam(value = "minDate", defaultValue = " ") String minDate,
                                                 @RequestParam(value = "maxDate", defaultValue = " ") String maxDate) {
        txt = URL.decodeParam(txt);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());

        List<Post> posts = postService.fullSearch(txt, min, max);
        return ResponseEntity.ok().body(posts);
    }
}
