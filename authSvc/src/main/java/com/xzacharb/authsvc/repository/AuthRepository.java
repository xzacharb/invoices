package com.xzacharb.authsvc.repository;

import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<UserDao, Long> {
}
