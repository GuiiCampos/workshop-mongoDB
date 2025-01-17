package com.workshop.resources;

import com.workshop.domain.User;
import com.workshop.dto.UserDTO;
import com.workshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {

        List<User> list = userService.findAll();
        List<UserDTO> users = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(users);
    }
}
