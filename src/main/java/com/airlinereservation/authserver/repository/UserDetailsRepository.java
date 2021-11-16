package com.airlinereservation.authserver.repository;

import com.airlinereservation.authserver.dto.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDetailsRepository extends CrudRepository<UserInfo, String> {

    public UserInfo findByUserNameAndEnabled(String username, short enabled);
    public List<UserInfo> findAllByEnabled(short enabled);

    public UserInfo findById(Integer id);

    public void deleteById(Integer id);
}
