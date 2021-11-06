package com.xzacharb.coresvc.infra.service.process;

public interface EvaluationService{

    /**
     * Run evaluation part to detect suspicious invoices for given city
     *
     * @param cityShort cityShortName
     */
    public void runEvaluation(String cityShort);

}
