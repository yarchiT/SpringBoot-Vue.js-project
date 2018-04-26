package com.kekker.app.service;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

import com.kekker.app.exception.CustomException;
import com.kekker.app.model.User;
import com.kekker.app.repository.UserRepository;
import com.kekker.app.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, new ArrayList<Role>());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }

    //todo(max): check if not exist email
    public String signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return jwtTokenProvider.createToken(user.getNickName(), new ArrayList<>());
    }

    public void delete(String username) {
        userRepository.deleteByNickName(username);
    }

    public User search(String nickName) {
        User user = userRepository.findByNickName(nickName);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByNickName(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

}