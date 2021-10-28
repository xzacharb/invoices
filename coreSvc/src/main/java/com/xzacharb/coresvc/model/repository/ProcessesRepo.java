package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.Process;
import org.springframework.data.repository.CrudRepository;

public interface ProcessesRepo extends CrudRepository<Process, Long> {
}
