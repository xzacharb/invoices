package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.Contractor;
import com.xzacharb.coresvc.model.dao.ManagementPerson;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagementPersonRepo extends CrudRepository<ManagementPerson, Long> {
    List<ManagementPerson> findByContractorObjDao(Contractor ContractorObjDao);
}
