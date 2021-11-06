package com.xzacharb.coresvc.infra.model.repository;

import com.xzacharb.coresvc.impl.model.dao.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, String> {
}
