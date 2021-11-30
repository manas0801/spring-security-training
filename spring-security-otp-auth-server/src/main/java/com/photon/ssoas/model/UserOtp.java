package com.photon.ssoas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userotp")
public class UserOtp {
    @Id
    private String username;
    private String otp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public UserOtp() {

    }

    public UserOtp(String username, String otp) {
        this.username = username;
        this.otp = otp;
    }
}
