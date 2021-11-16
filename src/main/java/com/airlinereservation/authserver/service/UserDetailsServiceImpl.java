package com.airlinereservation.authserver.service;

import com.airlinereservation.authserver.model.CustomUserDetails;
import com.airlinereservation.authserver.model.Users;
import com.airlinereservation.authserver.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


        Optional<Users> usersOptional = usersRepository.findByUsername(userName);

        usersOptional
            .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        return usersOptional
            .map(CustomUserDetails::new)
            .get();
    }
}
