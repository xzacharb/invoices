package com.xzacharb.coresvc.impl.component;

import com.xzacharb.coresvc.infra.service.microservices.AuthorizationService;
import com.xzacharb.coresvc.infra.service.microservices.CommunicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MicroserviceFacade {

    @Autowired
    public AuthorizationService authService;

    @Autowired
    public CommunicationService commService;
}
