package com.xzacharb.authsvc.controller;

import com.xzacharb.authsvc.model.AuthToken;
import com.xzacharb.authsvc.model.UserForm;
import com.xzacharb.authsvc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {
    private static final String about = "Welcome to Auth Service! \n" +
            "Requests: \n" +
            "POST /user/register\n" +
            "POST /user/login" +
            "POST /user/token_validation\n" +
            "POST /authorize/root\n" +
            "POST /authorize/admin\n" +
            "POST /authorize/user\n";
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserForm userForm) throws Exception {
        return ResponseEntity.ok(authService.save(userForm));
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserForm userForm) throws Exception {
        return ResponseEntity.ok(authService.save(userForm));
    }

    @RequestMapping(value = "/user/token_validation", method = RequestMethod.POST)
    public ResponseEntity<?> tokenValidation(@RequestBody AuthToken token) throws Exception {
        return ResponseEntity.ok(token);
    }

    @RequestMapping(value = "/authorize/root", method = RequestMethod.POST)
    public Boolean authorizationRoot(@RequestBody AuthToken token) throws Exception {
        return false;
    }

    @RequestMapping(value = "/authorize/admin", method = RequestMethod.POST)
    public Boolean authorizationAdmin(@RequestBody AuthToken token) throws Exception {
        return false;
    }

    @RequestMapping(value = "/authorize/user", method = RequestMethod.POST)
    public Boolean authorizationUser(@RequestBody AuthToken token) throws Exception {
        return false;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting() {
        return about;
    }

}
