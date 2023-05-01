package com.belyaeva.confectionerygradle.services.impl;

import com.belyaeva.confectionerygradle.entity.Role;
import com.belyaeva.confectionerygradle.entity.User;
import com.belyaeva.confectionerygradle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // FIX: Return Optional
        User user = userRepository.findByPhone(username);

        if (user == null)
            throw new UsernameNotFoundException("Пользователя с таким именем нет");

        Role[] userRoleEntities = user.getRoles().toArray(Role[]::new);
        String [] userRolesStr = new String[userRoleEntities.length];
        for (int i = 0; i < userRoleEntities.length; i++) {
            userRolesStr[i] = userRoleEntities[i].getName();
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(userRolesStr)
                .build();

        return userDetails;
    }
}
