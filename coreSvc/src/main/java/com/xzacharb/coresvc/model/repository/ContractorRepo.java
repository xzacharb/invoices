package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.ContractorDao;
import org.springframework.data.repository.CrudRepository;

public interface ContractorRepo extends CrudRepository<ContractorDao, Long> {
}
