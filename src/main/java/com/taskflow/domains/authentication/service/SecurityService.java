package com.taskflow.domains.authentication.service;

import com.taskflow.domains.authentication.dto.LoginDTO;
import com.taskflow.domains.authentication.dto.PostAuthDTO;
import com.taskflow.domains.authentication.repository.AuthRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService implements UserDetailsService {

    private final AuthRepository authRepository;

    public SecurityService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with: " + username));
    }

}
