package com.xzacharb.authsvc.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xzacharb.authsvc.model.UserForm;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length=128, unique=true, nullable = false)
    private String email;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private byte auth_level;

    public UserDao() {
    }

    public UserDao(String username, String password, byte auth_level) {
        this.email = username;
        this.password = password;
        this.auth_level = auth_level;
    }

    public UserDao(UserForm userDto, byte auth_level) {
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.auth_level = auth_level;
    }

    public byte getAuth_level() {
        return auth_level;
    }

    public void setAuth_level(byte auth_level) {
        this.auth_level = auth_level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

