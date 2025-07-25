package com.taskflow.domains.user;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String email;

    public UserDTO() {

    }

    public UserDTO(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
