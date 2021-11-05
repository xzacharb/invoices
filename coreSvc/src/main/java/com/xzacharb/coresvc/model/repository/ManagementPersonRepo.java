package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.Contractor;
import com.xzacharb.coresvc.model.dao.ManagementPersonDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ManagementPersonRepo extends CrudRepository<ManagementPersonDao, Long> {
    List<ManagementPersonDao> findByContractorObjDao(Contractor ContractorObjDao);
    Optional<ManagementPersonDao> findBySourceAndNameAndAddress(String source, String name, String address);
}
