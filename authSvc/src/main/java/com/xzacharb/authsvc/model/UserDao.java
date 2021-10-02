package com.xzacharb.authsvc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private byte auth_level;

    public UserDao() {
    }

    public UserDao(UserForm userDto, byte auth_level) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.auth_level = auth_level;
    }

    public byte getAuth_level() {
        return auth_level;
    }

    public void setAuth_level(byte auth_level) {
        this.auth_level = auth_level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

