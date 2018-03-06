package com.icrn.yamr.controllers;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
//import org.springframework.security.web.reactive.result.view.CsrfRequestDataValueProcessor;
//import org.springframework.security.web.server.csrf.CsrfToken;
//import org.springframework.web.server.ServerWebExchange;
//import sun.security.util.Password;

@RestController
@RequestMapping(path = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class test {

    private PasswordEncoder passwordEncoder;

    public test(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("pw/{string}")
    public Mono<String> passwordEncodeIt(@PathVariable("string") String string) {
        return Mono.just(this.passwordEncoder.encode(string));

    }

//    @GetMapping("csrf/token")
//    public Mono<CsrfToken> getToken(@ModelAttribute("csrf_token") CsrfToken csrfToken){
//        return Mono.just(csrfToken);
//    }


}
