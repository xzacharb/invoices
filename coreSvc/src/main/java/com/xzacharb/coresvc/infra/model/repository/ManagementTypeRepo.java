package com.xzacharb.coresvc.infra.model.repository;

import com.xzacharb.coresvc.impl.model.dao.ManagementType;
import org.springframework.data.repository.CrudRepository;

public interface ManagementTypeRepo extends CrudRepository<ManagementType, String> {
}
