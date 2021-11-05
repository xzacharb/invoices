package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.City;
import com.xzacharb.coresvc.model.dao.Contractor;
import com.xzacharb.coresvc.model.dao.InvoiceDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InvoicesRepo extends CrudRepository<InvoiceDao, Long> {
    List<InvoiceDao> findByCityAndContractor(City city, Contractor contractor);
    Optional<InvoiceDao> findByCityAndDescription(City city, String description);
}
