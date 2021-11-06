package com.xzacharb.coresvc.infra.model.repository;

import com.xzacharb.coresvc.impl.model.dao.Process;
import org.springframework.data.repository.CrudRepository;

public interface ProcessesRepo extends CrudRepository<Process, Long> {
}
