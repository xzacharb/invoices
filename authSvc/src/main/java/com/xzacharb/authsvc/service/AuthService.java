package com.xzacharb.authsvc.service;

import com.xzacharb.authsvc.model.AuthorizationLevel;
import com.xzacharb.authsvc.repository.UserDao;
import com.xzacharb.authsvc.model.UserForm;
import com.xzacharb.authsvc.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    AuthRepository authRepository;

    /*@Autowired
    private PasswordEncoder bcryptEncoder;*/

    public UserDao save(UserForm userForm) {
        String encryptedPassword = userForm.getPassword();/*bcryptEncoder.encode(userForm.getPassword());*/
        UserDao newUser = new UserDao(userForm.getEmail(),encryptedPassword, AuthorizationLevel.USER.byteValueOfLevel());
        return authRepository.save(newUser);
    }

}
