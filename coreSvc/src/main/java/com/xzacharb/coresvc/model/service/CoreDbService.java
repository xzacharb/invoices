package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreDbService {
    @Autowired
    public LegalFormRepo legalFormRepo;

    @Autowired
    public RoleRepo roleRepo;

    @Autowired
    public ContractorRepo contractorRepo;

    @Autowired
    public CityRepo cityRepo;

    @Autowired
    public ManagementPersonRepo companyPersonRepo;

    @Autowired
    public ManagementTypeRepo managementTypeRepo;
}
