package com.airlinereservation.authserver.contoller;

import com.airlinereservation.authserver.model.Role;
import com.airlinereservation.authserver.model.RoleName;
import com.airlinereservation.authserver.model.Users;
import com.airlinereservation.authserver.service.RoleService;
import com.airlinereservation.authserver.dto.UserInfo;
import com.airlinereservation.authserver.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController

public class UserController {
    @Autowired
    private UserInfoService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping("/users")
    public ResponseEntity<List<UserInfo>> getAllUser(@RequestHeader HttpHeaders requestHeader) {
        List<UserInfo> userInfos = userService.getAllActiveUserInfo();
        if (userInfos == null || userInfos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(userInfos);

    }


    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserInfo newUser ) {
        Role role = new Role();
        Users user = new Users();

        System.out.println(newUser.getUserName() + newUser.getRole());

        role.setRole(RoleName.valueOf(newUser.getRole()));
        user.setUsername(newUser.getUserName());
        user.setPassword(newUser.getPassword());
        user.setEmail(newUser.getEmail());
        user.setActive(newUser.getActive());
        roleService.saveRole(role);
        user.setRoles(Collections.singleton(role));

        Users result = userService.addUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body("User registered successfully");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserInfo> updateUser(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
        return ResponseEntity.ok(userService.updateUser(id, userRecord));
    }

    @PutMapping("/users/changePassword/{id}")
    public ResponseEntity<UserInfo> updateUserPassword(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
        return ResponseEntity.ok(userService.updatePassword(id, userRecord));
    }

    @PutMapping("/users/changeRole/{id}")
    public ResponseEntity<UserInfo> updateUserRole(@RequestBody UserInfo userRecord, @PathVariable Integer id) {
        return ResponseEntity.ok(userService.updateRole(id, userRecord));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable Integer id) {
        UserInfo userInfo = userService.getUserInfoById(id);
        if (userInfo == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }


}
