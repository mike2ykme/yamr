package com.icrn.yamr.security;

import com.icrn.yamr.domain.User;
import com.icrn.yamr.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.Collection;


@Component
public class ReactiveUserDetailServiceRepo implements ReactiveUserDetailsService{
    private final UserRepository userRepo;

    public ReactiveUserDetailServiceRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Mono<UserDetails> findByUsername(String s) {
        return this.userRepo
                .findByEmail(s)
                .map(CustomUserDetails::new);

    }

    static class CustomUserDetails extends User implements UserDetails {
        public CustomUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
