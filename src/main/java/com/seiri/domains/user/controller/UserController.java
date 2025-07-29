package com.seiri.domains.user.controller;

import com.seiri.domains.user.dto.UserDTO;
import com.seiri.domains.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sc/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String me() {
        return this.userService.me();
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> info() {
        UserDTO response = this.userService.getCurrentUserDTO();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
