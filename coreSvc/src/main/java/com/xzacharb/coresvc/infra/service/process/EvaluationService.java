package com.xzacharb.coresvc.infra.service.process;

import com.xzacharb.coresvc.impl.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.infra.component.Rules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface EvaluationService{

    /**
     * Run evaluation part to detect suspicious invoices for given city
     *
     * @param cityShort cityShortName
     */
    public void runEvaluation(String cityShort);

}
