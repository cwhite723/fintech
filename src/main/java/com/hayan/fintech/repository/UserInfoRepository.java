package com.hayan.fintech.repository;

import com.hayan.fintech.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    boolean existsByUserKey(String userKey);
    boolean existsByUserRegistrationNumber(String userRegistrationNumber);
    Optional<UserInfo> findByUserKey(String userKey);
}
