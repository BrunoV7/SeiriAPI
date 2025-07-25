package com.taskflow.domains.auth;

import com.taskflow.domains.auth.dto.LoginDTO;
import com.taskflow.domains.auth.dto.NewUserDTO;
import com.taskflow.domains.auth.dto.PostAuthDTO;
import com.taskflow.domains.auth.exception.InvalidEmailException;
import com.taskflow.domains.auth.exception.InvalidEmailOrPasswordException;
import com.taskflow.domains.auth.exception.InvalidRegisterFieldsException;
import com.taskflow.domains.auth.exception.ShortRegisterPasswordException;
import com.taskflow.domains.user.User;
import com.taskflow.utils.check;
import com.taskflow.utils.hasher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
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
        String hashedPassword = hasher.hash(newUser.getPassword());
        User user = new User(newUser.getFirstName(), newUser.getLastName(), hashedPassword, newUser.getEmail());
        try{
            user = authRepository.save(user);
        } catch(Exception e){
            throw new InvalidRegisterFieldsException("Email already exists or is invalid");
        }

        return new PostAuthDTO(
                UUID.randomUUID(),
                "Some Token", //later this will be JWT
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()

        );
    }

    public PostAuthDTO login(LoginDTO loginDTO) {
        Optional<User> login = authRepository.findByEmail(loginDTO.getEmail());
        if (login.isEmpty()) {
            throw new InvalidEmailOrPasswordException("User not found");
        }
        User user = login.get();
        if (!hasher.check(loginDTO.getPassword(), user.getPassword())) {
            throw new InvalidEmailOrPasswordException("Email or password is incorrect");
        }

        return new PostAuthDTO(
                UUID.randomUUID(),
                "Some Token", //later this will be JWT
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()

        );
    }

}
