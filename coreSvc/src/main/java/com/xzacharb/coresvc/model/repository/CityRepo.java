package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepo extends CrudRepository<City, String> {
}
