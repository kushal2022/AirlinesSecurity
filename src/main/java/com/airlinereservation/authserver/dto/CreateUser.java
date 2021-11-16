package com.airlinereservation.authserver.dto;

import lombok.Data;

@Data
public class CreateUser {

    private String email;
    private String username;
    private String password;
    private String lastName;
    private int active;
    private String roleName;
}
