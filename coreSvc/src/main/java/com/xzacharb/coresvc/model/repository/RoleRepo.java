package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, String> {
}
