package com.xzacharb.coresvc.model.service;

import com.xzacharb.coresvc.common.AuthorizationLevel;
import com.xzacharb.coresvc.model.data.UserData;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public UserData setAuthorizationLevel(UserData user){
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
