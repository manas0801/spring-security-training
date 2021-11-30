package com.photon.ssoas.repository;

import com.photon.ssoas.model.UserOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserOtpRepository extends JpaRepository<UserOtp,String> {

    Optional<UserOtp> findByUsername(String username);
}
