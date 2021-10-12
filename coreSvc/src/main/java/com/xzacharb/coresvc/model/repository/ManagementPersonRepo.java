package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.ContractorDao;
import com.xzacharb.coresvc.model.dao.ManagementPersonDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagementPersonRepo extends CrudRepository<ManagementPersonDao, Long> {
    List<ManagementPersonDao> findByContractorObjDao(ContractorDao ContractorObjDao);
}
