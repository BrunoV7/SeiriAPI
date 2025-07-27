package com.taskflow.domains.user.service;

import com.taskflow.domains.user.User;
import com.taskflow.domains.user.dto.UserDTO;
import com.taskflow.domains.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String me() {
        UUID id = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            return null;
        }else{
            return "User is: " + user.getFirstName() + " " +  user.getLastName();
        }
    }

    public User getCurrentUser() {
        UUID id = UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new UsernameNotFoundException("No user found!");
        }
        return user;
    }

    public UserDTO getCurrentUserDTO() {
        User user = getCurrentUser();
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
