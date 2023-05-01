package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.RoleEntity;
import com.belyaeva.confectionerygradle.entity.UserEntity;
import com.belyaeva.confectionerygradle.repository.UserRepository;
import com.belyaeva.confectionerygradle.services.abstractions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean saveUser(UserEntity user) {
        // FIX: Return Optional
        UserEntity userFromDB = userRepository.findByPhoneLike(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new RoleEntity("USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public UserEntity getTempUser(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal.equals("anonymousUser"))
            return null;
        else{
            UserDetails userDetails = (UserDetails) principal;
            return userRepository.findByPhoneLike(userDetails.getUsername());
        }
    }
}
