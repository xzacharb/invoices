package com.xzacharb.coresvc.infra.model.repository;

import com.xzacharb.coresvc.impl.model.dao.LegalForm;
import org.springframework.data.repository.CrudRepository;

public interface LegalFormRepo extends CrudRepository<LegalForm,String> {
}
