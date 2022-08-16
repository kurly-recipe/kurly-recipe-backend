package com.example.demo.service;

import com.example.demo.model.dto.UserCreateRequest;
import com.example.demo.model.dto.UserResponse;
import com.example.demo.model.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException(username + ": not found");

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }

    public UserResponse createUser(UserCreateRequest userCreateRequest) {

        User user = User.builder()
                .email(userCreateRequest.getEmail())
                .build();

        user.setEncryptedPwd(passwordEncoder.encode(userCreateRequest.getPassword()));

        userRepository.save(user);

        UserResponse userResponse = UserResponse.builder()
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();

        return userResponse;
    }

}
