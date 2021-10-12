package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.LegalFormDao;
import org.springframework.data.repository.CrudRepository;

public interface LegalFormRepo extends CrudRepository<LegalFormDao,String> {
}
