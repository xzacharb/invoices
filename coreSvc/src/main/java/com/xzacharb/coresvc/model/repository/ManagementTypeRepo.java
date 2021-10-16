package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.ManagementType;
import org.springframework.data.repository.CrudRepository;

public interface ManagementTypeRepo extends CrudRepository<ManagementType, String> {
}
