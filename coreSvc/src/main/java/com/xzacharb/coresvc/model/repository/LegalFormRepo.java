package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.LegalForm;
import org.springframework.data.repository.CrudRepository;

public interface LegalFormRepo extends CrudRepository<LegalForm,String> {
}
