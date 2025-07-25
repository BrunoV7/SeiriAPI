package com.taskflow.domains.auth;

import com.taskflow.domains.auth.dto.LoginDTO;
import com.taskflow.domains.auth.dto.NewUserDTO;
import com.taskflow.domains.auth.dto.PostAuthDTO;
import com.taskflow.domains.user.UserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
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
