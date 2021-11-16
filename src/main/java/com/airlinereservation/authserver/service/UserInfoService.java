package com.airlinereservation.authserver.service;

import com.airlinereservation.authserver.model.CustomUserDetails;
import com.airlinereservation.authserver.model.Users;
import com.airlinereservation.authserver.repository.UserDetailsRepository;
import com.airlinereservation.authserver.repository.UsersRepository;
import com.airlinereservation.authserver.dto.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;



@Service
@Transactional
public class UserInfoService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UsersRepository usersRepository;


    private final PasswordEncoder passwordEncoder;

    public UserInfoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public Users getUserByUserName(String userName) {
        Optional<Users> usersOptional = usersRepository.findByUsername(userName);

        usersOptional
            .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return usersOptional
            .map(CustomUserDetails::new)
            .get();
    }

    public UserInfo getUserInfoByUserName(String userName) {
        short enabled = 1;
        return userDetailsRepository.findByUserNameAndEnabled(userName, enabled);
    }

    public List<UserInfo> getAllActiveUserInfo() {
        return userDetailsRepository.findAllByEnabled((short) 1);
    }

    public UserInfo getUserInfoById(Integer id) {
        return userDetailsRepository.findById(id);
    }

    public Users addUser(Users userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return usersRepository.save(userInfo);
    }

    public UserInfo updateUser(Integer id, UserInfo userRecord) {
        UserInfo userInfo = userDetailsRepository.findById(id);
        userInfo.setUserName(userRecord.getUserName());
        userInfo.setPassword(userRecord.getPassword());
        userInfo.setRole(userRecord.getRole());
        userInfo.setEnabled(userRecord.getEnabled());
        return userDetailsRepository.save(userInfo);
    }

    public void deleteUser(Integer id) {
        userDetailsRepository.deleteById(id);
    }

    public UserInfo updatePassword(Integer id, UserInfo userRecord) {
        UserInfo userInfo = userDetailsRepository.findById(id);
        userInfo.setPassword(userRecord.getPassword());
        return userDetailsRepository.save(userInfo);
    }

    public UserInfo updateRole(Integer id, UserInfo userRecord) {
        UserInfo userInfo = userDetailsRepository.findById(id);
        userInfo.setRole(userRecord.getRole());
        return userDetailsRepository.save(userInfo);
    }

}
