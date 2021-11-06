package com.xzacharb.coresvc.impl.service.microservices;

import com.xzacharb.coresvc.impl.common.AuthorizationLevel;
import com.xzacharb.coresvc.impl.model.dto.UserData;
import com.xzacharb.coresvc.infra.service.microservices.AuthorizationService;
import org.springframework.stereotype.Service;

@Service
public class DefaultAuthorizationService implements AuthorizationService {

    public UserData getUserAuthorizationLevel(UserData user){
        if (hasToken(user)) {
            //TODO connect to AUTH SERVER api with token and get authorization level
            return new UserData(user.getToken(),AuthorizationLevel.levelOfByteValue((byte) 1));
        }
        return user;
    }

    private boolean hasToken(UserData user) {
        return !user.getToken().isEmpty();
    }
}
