package com.airlinereservation.authserver.repository;

import com.airlinereservation.authserver.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    //@Query(value = "SELECT * FROM `user` WHERE email = :username OR `name` = :username", nativeQuery=true)
    Optional<Users> findByUsername(String username);

}
