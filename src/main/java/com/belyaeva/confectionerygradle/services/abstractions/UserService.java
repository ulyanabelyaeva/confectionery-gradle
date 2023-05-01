package com.belyaeva.confectionerygradle.services.abstractions;

import com.belyaeva.confectionerygradle.entity.User;

public interface UserService {

    User getTempUser();

    boolean saveUser(User user);

}
