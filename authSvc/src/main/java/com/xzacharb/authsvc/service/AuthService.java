package com.xzacharb.authsvc.service;

import com.xzacharb.authsvc.common.AuthCommonMethods;
import com.xzacharb.authsvc.common.AuthorizationLevel;
import com.xzacharb.authsvc.model.UserDao;
import com.xzacharb.authsvc.model.UserForm;
import com.xzacharb.authsvc.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    public UserDao save(UserForm userForm) {
        UserDao userDao = new UserDao(userForm, AuthCommonMethods.valueOfAuthorizationLevel(AuthorizationLevel.USER));
        return authRepository.save(userDao);
    }

}
