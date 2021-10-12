package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.ManagementPersonDao;
import org.springframework.data.repository.CrudRepository;

public interface ManagementPersonRepo extends CrudRepository<ManagementPersonDao, Long> {
}
