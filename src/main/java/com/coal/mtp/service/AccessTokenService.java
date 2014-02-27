package com.coal.mtp.service;

import com.coal.mtp.entity.AccessToken;

public interface AccessTokenService {

    AccessToken create();
    
    boolean isValide(String token);
}
