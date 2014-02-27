package com.coal.mtp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.AccessToken;

public interface AccessTokenRepository extends CrudRepository<AccessToken, Long> {

    AccessToken findByToken(String token);
}
