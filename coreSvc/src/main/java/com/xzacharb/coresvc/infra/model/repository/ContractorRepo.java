package com.xzacharb.coresvc.infra.model.repository;

import com.xzacharb.coresvc.impl.model.dao.Contractor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContractorRepo extends CrudRepository<Contractor, Long> {
    public Optional<Contractor> findByIco(String ico);
}
