package com.xzacharb.coresvc.infra.model.repository;

import com.xzacharb.coresvc.impl.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import org.springframework.data.repository.CrudRepository;

public interface EvaluatedResultRepo extends CrudRepository<EvaluatedResult, Long> {
    EvaluatedResult findByInvoiceDaoAndEvaluatorNameShort(InvoiceDao invoiceDao, String evaluator_name_short);
}
