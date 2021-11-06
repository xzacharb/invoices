package com.xzacharb.coresvc.impl.service.process;

import com.xzacharb.coresvc.impl.component.composition.RepositoryFacade;
import com.xzacharb.coresvc.infra.service.process.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultProcessesService implements ProcessService {
    @Autowired
    RepositoryFacade repository;

    @Autowired
    DefaultEvaluationService evaluationService;

    @Autowired
    DefaultDetectionService detectionService;

    @Override
    public void runDetection(String cityShort) {
        detectionService.runDetection(cityShort);
    }

    @Override
    public void runEvaluation(String cityShort) {
        evaluationService.runEvaluation(cityShort);
    }

}
