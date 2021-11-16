package com.airlinereservation.authserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 25)
    private Integer id;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "active", length = 50)
    private int active;

    @Column(name = "username", length = 50)
    private String userName;

    @Column(name = "password", length = 800)
    private String password;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "enabled")
    private short enabled;


}
