package com.xzacharb.authsvc.controller;

import com.xzacharb.authsvc.model.UserForm;
import com.xzacharb.authsvc.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*@RestController
@CrossOrigin*/
public class AuthController {
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserForm userForm) throws Exception {
        return ResponseEntity.ok(authService.save(userForm));
    }

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting() {
        return "Welcome!";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
