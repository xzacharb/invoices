package com.xzacharb.coresvc.infra.service.microservices;

import com.xzacharb.coresvc.impl.model.dto.UserData;

public interface AuthorizationService {
    public UserData getUserAuthorizationLevel(UserData user);
}
