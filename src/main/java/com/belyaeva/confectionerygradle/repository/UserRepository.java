package com.belyaeva.confectionerygradle.repository;

import com.belyaeva.confectionerygradle.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByPhoneLike(String username);
}