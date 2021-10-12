package com.xzacharb.coresvc.model.repository;

import com.xzacharb.coresvc.model.dao.InvoiceDao;
import org.springframework.data.repository.CrudRepository;

public interface InvoicesRepo extends CrudRepository<InvoiceDao, Long> {
}
