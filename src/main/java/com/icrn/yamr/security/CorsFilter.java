package com.icrn.yamr.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

//https://thepracticaldeveloper.com/2017/11/04/full-reactive-stack-with-spring-webflux-and-angularjs/#Enabling_CORS_in_Spring_WebFlux
@Configuration
public class CorsFilter implements WebFilter {
    @Override
    public Mono<Void> filter(final ServerWebExchange serverWebExchange, final WebFilterChain webFilterChain) {
        // Adapted from https://sandstorm.de/de/blog/post/cors-headers-for-spring-boot-kotlin-webflux-reactor-project.html
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
//        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
        serverWebExchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range,Authorization,X-CSRF-TOKEN");
        if (serverWebExchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            serverWebExchange.getResponse().getHeaders().add("Access-Control-Max-Age", "1728000");
            serverWebExchange.getResponse().setStatusCode(HttpStatus.NO_CONTENT);
//            return Mono.empty();
//        } else {
            serverWebExchange.getResponse().getHeaders().add("Access-Control-Expose-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        }   return webFilterChain.filter(serverWebExchange);
//        }
    }
}
