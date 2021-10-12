package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.CityDao;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<CityDao, String> {
}
