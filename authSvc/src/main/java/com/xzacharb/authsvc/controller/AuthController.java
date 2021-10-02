package com.xzacharb.authsvc.controller;

import com.xzacharb.authsvc.model.UserForm;
import com.xzacharb.authsvc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
public class AuthController {
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserForm user) {
        return ResponseEntity.ok(authService.save(user));
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String hello() {
        return "Welcome!";
    }
}
