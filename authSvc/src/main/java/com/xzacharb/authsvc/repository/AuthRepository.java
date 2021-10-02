package com.xzacharb.authsvc.repository;

import com.xzacharb.authsvc.model.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<UserDao, Long> {
}
