package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.model.repository.LegalFormRepo;
import com.xzacharb.coresvc.model.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoreDbService {
    @Autowired
    public LegalFormRepo legalFormRepo;

    @Autowired
    public RoleRepo roleRepo;
}
