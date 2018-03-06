package com.icrn.yamr.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import reactor.core.publisher.Mono;


@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
//                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/users").access(this::isRob)
                .pathMatchers("/login", "/signup", "/webjars/**").permitAll()
//                .pathMatchers("/login", "/signup", "/webjars/**","/test/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic().and()
                .formLogin()
//                .loginPage("/login");
                ;
        return http.build();
//
//        return http.authorizeExchange()
//                .anyExchange().authenticated()
//                .and().formLogin()
//                .and().build();
    }

    private Mono<AuthorizationDecision> isRob(Mono<Authentication> authentication,
                                              AuthorizationContext authorizationContext) {
        return authentication
                .map(Authentication::getName)
                .map(username -> username.startsWith("rob@") || username.startsWith("test"))
                .map(AuthorizationDecision::new);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return NoOpPasswordEncoder.getInstance();
    }
}