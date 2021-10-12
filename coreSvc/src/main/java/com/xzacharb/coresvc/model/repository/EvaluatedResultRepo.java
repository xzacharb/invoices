package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.EvaluatedResultDao;
import org.springframework.data.repository.CrudRepository;

public interface EvaluatedResultRepo extends CrudRepository<EvaluatedResultDao, Long> {
}
