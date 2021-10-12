package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.RoleDao;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<RoleDao, Long> {
}
