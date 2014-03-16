package com.coal.mtp.service;

import com.coal.mtp.dto.ProfileDto;

public interface ProfileService {

    ProfileDto findProfileDto(Long surfaceId, Long tunnelId);
}
