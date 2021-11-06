package com.xzacharb.coresvc.impl.service.process;

import com.xzacharb.coresvc.impl.component.composition.MicroserviceFacade;
import com.xzacharb.coresvc.impl.component.composition.RepositoryFacade;
import com.xzacharb.coresvc.impl.model.dao.City;
import com.xzacharb.coresvc.impl.model.dao.Process;
import com.xzacharb.coresvc.infra.service.process.DetectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DefaultDetectionService implements DetectionService {
    @Autowired
    RepositoryFacade repository;
    @Autowired
    MicroserviceFacade msFacade;

    @Override
    public void runDetection(String cityShort) {
        Date start = new Date();
        City city = repository.cityRepo.findById(cityShort).orElse(null);
        msFacade.commService.getCityInvoices(city.getCity_short());
        Process process = new Process("Detection", "Dolovanie faktur pre mesto: " + city.getCity_name(), "SUCCESS", start, new Date());
        repository.processesRepo.save(process);

    }
}
