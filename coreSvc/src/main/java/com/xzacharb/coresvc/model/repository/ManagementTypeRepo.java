package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.ManagementTypeDao;
import org.springframework.data.repository.CrudRepository;

public interface ManagementTypeRepo extends CrudRepository<ManagementTypeDao, String> {
}
