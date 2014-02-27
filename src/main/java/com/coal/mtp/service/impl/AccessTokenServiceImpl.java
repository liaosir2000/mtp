package com.coal.mtp.service.impl;

import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.AccessToken;
import com.coal.mtp.repositories.AccessTokenRepository;
import com.coal.mtp.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private AccessTokenRepository accessTokenRepo;
    
    @Value("${access.token.expire:3600000}")//默认1小时
    private long expireTime;
    
    public AccessToken create() {
        AccessToken token = new AccessToken();
        DateTime now = new DateTime();
        token.setToken(UUID.randomUUID().toString());
        token.setCreateTime(now);
        token.setLastAccessTime(now);
        token = accessTokenRepo.save(token);
        return token;
    }

    public boolean isValide(String token) {
        AccessToken accessToken = accessTokenRepo.findByToken(token);
        if (accessToken != null) {
            long elapsed = System.currentTimeMillis() - accessToken.getLastAccessTime().getMillis();
            if (elapsed > expireTime) {
                accessTokenRepo.delete(accessToken.getId());
                return false;
            } else {
                accessToken.setLastAccessTime(new DateTime());
                accessTokenRepo.save(accessToken);
                return true;
            }
        } else {
            return false;
        }
    }
}
