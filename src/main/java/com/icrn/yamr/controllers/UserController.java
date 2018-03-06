package com.icrn.yamr.controllers;

import com.icrn.yamr.domain.User;
import com.icrn.yamr.repositories.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

//    public UserController(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }

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

    @PostMapping("/create")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }
//    public Mono<Recipe> insertRecipe(@Valid @RequestBody Recipe recipe){
//        return recipeRepository.save(recipe);
//    }


}
