package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.Contractor;
import org.springframework.data.repository.CrudRepository;

public interface ContractorRepo extends CrudRepository<Contractor, Long> {
}
