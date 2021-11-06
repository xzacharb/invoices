package com.xzacharb.coresvc.impl.service.process;

import com.xzacharb.coresvc.infra.service.process.DetectionService;
import com.xzacharb.coresvc.infra.service.process.EvaluationService;
import com.xzacharb.coresvc.infra.service.process.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultProcessesService implements ProcessService {

    @Autowired
    EvaluationService evaluationService;

    @Autowired
    DetectionService detectionService;

    @Override
    public void runDetection(String cityShort) {
        detectionService.runDetection(cityShort);
    }

    @Override
    public void runEvaluation(String cityShort) {
        evaluationService.runEvaluation(cityShort);
    }

}
