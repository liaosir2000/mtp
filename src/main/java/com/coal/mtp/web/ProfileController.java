package com.coal.mtp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.dto.ProfileDto;
import com.coal.mtp.service.ProfileService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private ProfileService profileService;
	
    @RequestMapping()
    public String init() {
        return "profile";
    }
    
    @RequestMapping(value = "/{surfaceId}/{tunnelId}")
    @ResponseBody
    public ProfileDto getProfileDto(@PathVariable("surfaceId")Long surfaceId, @PathVariable("tunnelId") Long tunnelId) {
        ProfileDto dto = profileService.findProfileDto(surfaceId, tunnelId);
        return dto;
    }
}
