package com.xzacharb.coresvc.impl.component.composition;

import com.xzacharb.coresvc.infra.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryFacade {
    @Autowired
    public LegalFormRepo legalFormRepo;

    @Autowired
    public RoleRepo roleRepo;

    @Autowired
    public ContractorRepo contractorRepo;

    @Autowired
    public CityRepo cityRepo;

    @Autowired
    public ProcessesRepo processesRepo;

    @Autowired
    public ManagementPersonRepo managementPersonRepo;

    @Autowired
    public ManagementTypeRepo managementTypeRepo;

    @Autowired
    public InvoicesRepo invoicesRepo;

    @Autowired
    public EvaluatedResultRepo evaluatedResultRepo;
}
