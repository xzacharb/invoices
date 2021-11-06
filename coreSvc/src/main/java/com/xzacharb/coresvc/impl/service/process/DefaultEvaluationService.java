package com.xzacharb.coresvc.impl.service.process;

import com.xzacharb.coresvc.impl.component.EvaluationComponent;
import com.xzacharb.coresvc.impl.component.composition.RepositoryFacade;
import com.xzacharb.coresvc.impl.model.dao.City;
import com.xzacharb.coresvc.impl.model.dao.Contractor;
import com.xzacharb.coresvc.impl.model.dao.EvaluatedResult;
import com.xzacharb.coresvc.impl.model.dao.InvoiceDao;
import com.xzacharb.coresvc.impl.model.dao.Process;
import com.xzacharb.coresvc.infra.service.process.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DefaultEvaluationService implements EvaluationService {

    @Autowired
    private EvaluationComponent evaluationComponent;
    @Autowired
    private RepositoryFacade repository;


    @Override
    public void runEvaluation(String cityShort) {
        City city = repository.cityRepo.findById(cityShort).orElse(null);
        Date start = new Date();

        for (Contractor contractor : repository.contractorRepo.findAll()) {
            List<EvaluatedResult> resultList = new ArrayList<>();
            List<InvoiceDao> invoiceDaos = new ArrayList<>();

            invoiceDaos.addAll(repository.invoicesRepo.findByCityAndContractor(city, contractor));
            resultList.addAll(evaluationComponent.filteredSumOverThreshold(invoiceDaos));
            resultList.addAll(evaluationComponent.totalSumOverThreshold(invoiceDaos));

            resultList.forEach(evaluatedDao -> {
                EvaluatedResult evaluatedResult = repository.evaluatedResultRepo.findByInvoiceDaoAndEvaluatorNameShort(evaluatedDao.getInvoiceDao(), evaluatedDao.getEvaluatorNameShort());
                if (evaluatedResult == null)
                    repository.evaluatedResultRepo.save(evaluatedDao);
            });
        }

        com.xzacharb.coresvc.impl.model.dao.Process process = new Process("Evaluation", "Vyhodnocovanie faktur pre mesto: " + city.getCity_name(), "SUCCESS", start, new Date());
        repository.processesRepo.save(process);
    }
}
