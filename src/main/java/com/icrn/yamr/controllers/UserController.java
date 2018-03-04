package com.icrn.yamr.controllers;

import com.icrn.yamr.domain.User;
import com.icrn.yamr.repositories.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public Flux<User> users(){
        return this.userRepo.findAll();
    }

    @GetMapping("/id/{id}")
    public Mono<User> getUserById(@PathVariable("id") String id){
        return this.userRepo.findById(id);
    }

    @GetMapping("/email/{email}")
    public Mono<User> getUserByEmail(@PathVariable("email") String email){
        return this.userRepo.findByEmail(email);
    }

}
