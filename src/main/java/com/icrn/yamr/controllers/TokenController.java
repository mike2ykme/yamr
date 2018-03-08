//package com.icrn.yamr.controllers;
//
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
//import org.springframework.security.web.server.csrf.CsrfToken;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/getToken")
////@CrossOrigin("*")
//public class TokenController {
//
//    @GetMapping
//    public Mono<CsrfToken> getToken(@ModelAttribute("csrf_token") CsrfToken csrfToken) {
//        return Mono.just(csrfToken);
//        //Really I was just looking at how the security advice worked and thought if I made a change to it I should be able to pull this info as well
//    }
//}
//
////https://stackoverflow.com/questions/27182701/how-do-i-send-spring-csrf-token-from-postman-rest-client
//// I used this site as a basis for using the csrf token in a post request
//
////https://stackoverflow.com/questions/36339552/get-csrf-in-spring-controller
////This site led me to think I could just put the csrf token in a rest controller to get it
//
