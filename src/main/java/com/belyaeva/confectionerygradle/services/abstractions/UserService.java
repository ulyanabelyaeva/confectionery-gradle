package com.belyaeva.confectionerygradle.services.abstractions;

import com.belyaeva.confectionerygradle.entity.UserEntity;

public interface UserService {

    UserEntity getTempUser();

    boolean saveUser(UserEntity user);

}
