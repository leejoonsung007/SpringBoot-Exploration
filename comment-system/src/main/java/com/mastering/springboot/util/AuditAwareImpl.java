package com.mastering.springboot.util;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Component
//load the currently logged-in user
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor(){
        //SecurityContextHolder is responsible for holding the authentication object for a logged in user
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()){
            return Optional.empty();
        }
        return Optional.of(((User) authentication.getPrincipal()).getUsername());
    }

}
