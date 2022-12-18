package com.belyaeva.confectionerygradle.model.services.abstractions;

import com.belyaeva.confectionerygradle.model.entity.UserEntity;

public interface UserService {

    UserEntity getTempUser();

    boolean saveUser(UserEntity user);

}
