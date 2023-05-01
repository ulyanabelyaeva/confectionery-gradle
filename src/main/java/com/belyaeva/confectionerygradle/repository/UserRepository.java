package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String username);
}