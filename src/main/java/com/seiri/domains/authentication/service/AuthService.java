package com.seiri.domains.authentication.service;

import com.seiri.domains.authentication.dto.LoginDTO;
import com.seiri.domains.authentication.dto.NewUserDTO;
import com.seiri.domains.authentication.dto.PostAuthDTO;
import com.seiri.domains.authentication.exception.InvalidEmailException;
import com.seiri.domains.authentication.exception.InvalidRegisterFieldsException;
import com.seiri.domains.authentication.exception.ShortRegisterPasswordException;
import com.seiri.domains.authentication.repository.AuthRepository;
import com.seiri.domains.user.User;
import com.seiri.domains.user.dto.UserRole;
import com.seiri.utils.check;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService{

    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthRepository authRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authRepository = authRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    private boolean userExists(String email) {
        return this.authRepository.existsByEmail(email);
    }

    public PostAuthDTO registerNewUser(NewUserDTO newUser) {
        if(newUser.getEmail() == null || newUser.getPassword() == null) {
            throw new InvalidRegisterFieldsException("Email or password is required");
        }
        if(newUser.getPassword().length() < 6) {
            throw new ShortRegisterPasswordException("Password must be at least 6 characters");
        }
        if (!check.isEmailValid(newUser.getEmail())) {
            throw new InvalidEmailException("Email format is invalid");
        }
        if (userExists(newUser.getEmail())) {
            throw new InvalidEmailException("Email already exists");
        }
        String hashedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
        User user = new User(newUser.getFirstName(), newUser.getLastName(), hashedPassword, newUser.getEmail(), UserRole.USER, "");
        try{
            user = authRepository.save(user);
        } catch(Exception e){
            throw new InvalidRegisterFieldsException("Email already exists or is invalid");
        }
        var token = jwtService.generateToken(user);
        return new PostAuthDTO(
                UUID.randomUUID(),
                token,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                ""
        );
    }


    public PostAuthDTO login(LoginDTO login) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        User user = (User) auth.getPrincipal();
        var token = jwtService.generateToken(user);
        return new PostAuthDTO(
                UUID.randomUUID(),
                token,
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
        ""
        );
    }

}
