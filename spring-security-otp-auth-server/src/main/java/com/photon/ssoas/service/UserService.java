package com.photon.ssoas.service;

import com.photon.ssoas.model.User;
import com.photon.ssoas.model.UserOtp;
import com.photon.ssoas.repository.UserOtpRepository;
import com.photon.ssoas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserOtpRepository userOtpRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void authUser(User user) {

        Optional<User> optionalPersistedUser= userRepository.findByUsername(user.getUsername());
       if( optionalPersistedUser.isPresent()){
          User persistedUser= optionalPersistedUser.get();
           if(passwordEncoder.matches(user.getPassword(),persistedUser.getPassword())){
               sendOtp(user);
           }
           else{
               throw new BadCredentialsException("Bad Credential ");
           }
    } else {
      throw new UsernameNotFoundException("User Not Found");
        }
    }

    public Boolean checkOtp(UserOtp userOtp){

        Optional<UserOtp> optionalUserOtp= userOtpRepository.findByUsername(userOtp.getUsername());
        if(optionalUserOtp.isPresent()){
            UserOtp persistedUserOtp= optionalUserOtp.get();
            if(persistedUserOtp.getOtp().equals(userOtp.getOtp())){
                return true;
            }
            return false;
        }
        return false;
    }

    private void sendOtp(User user){
       String otp= generateOtp();
       Optional<UserOtp> optionalUserOtp= userOtpRepository.findByUsername(user.getUsername());
        if(optionalUserOtp.isPresent()){
            UserOtp persistedUserOtp= optionalUserOtp.get();
            persistedUserOtp.setOtp(otp);
            userOtpRepository.save(persistedUserOtp);
        }
        else{
            UserOtp userOtp= new UserOtp(user.getUsername(), otp);
            userOtpRepository.save(userOtp);
        }
    }

    private String generateOtp(){

        SecureRandom secureRandom= new SecureRandom();
       Integer random= secureRandom.nextInt(9000) +1000;
       return  String.valueOf(random);
    }
}
