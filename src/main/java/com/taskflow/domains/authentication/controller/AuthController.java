package com.taskflow.domains.authentication.controller;

import com.taskflow.domains.authentication.service.AuthService;
import com.taskflow.domains.authentication.dto.LoginDTO;
import com.taskflow.domains.authentication.dto.NewUserDTO;
import com.taskflow.domains.authentication.dto.PostAuthDTO;
import com.taskflow.domains.authentication.service.SecurityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final SecurityService securityService;

    public AuthController(AuthService authService, SecurityService securityService) {
        this.authService = authService;
        this.securityService = securityService;
    }

    @PostMapping("/register")
    public ResponseEntity<PostAuthDTO> register(@RequestBody @Valid NewUserDTO user) {
        PostAuthDTO response = authService.registerNewUser(user);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<PostAuthDTO> login(@RequestBody @Valid LoginDTO user) {
        PostAuthDTO response = authService.login(user);
        return ResponseEntity.status(201).body(response);
    }

}
