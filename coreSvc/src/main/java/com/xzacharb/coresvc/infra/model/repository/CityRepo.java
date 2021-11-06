package com.xzacharb.coresvc.infra.model.repository;

import com.xzacharb.coresvc.impl.model.dao.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<City, String> {
}
