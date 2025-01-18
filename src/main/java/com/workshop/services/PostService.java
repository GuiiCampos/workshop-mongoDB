package com.workshop.services;

import com.workshop.domain.Post;
import com.workshop.repository.PostRepository;
import com.workshop.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post findById(String id) {
        Optional<Post> user = postRepository.findById(id);

        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    /*public List<Post> findByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase( title);
    }*/

    public List<Post> findByTitle(String title) {
        return postRepository.searchTitle( title);
    }

    public List<Post> fullSearch(String title, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(title, minDate, maxDate);
    }
}
