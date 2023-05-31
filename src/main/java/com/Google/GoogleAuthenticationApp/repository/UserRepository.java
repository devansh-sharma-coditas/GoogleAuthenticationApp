package com.Google.GoogleAuthenticationApp.repository;

import com.Google.GoogleAuthenticationApp.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo,String> {
}
