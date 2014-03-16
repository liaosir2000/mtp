package com.coal.mtp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.dto.FormDto;
import com.coal.mtp.dto.ProfileDto;
import com.coal.mtp.entity.Form;
import com.coal.mtp.entity.PointConfig;
import com.coal.mtp.repositories.FormRepository;
import com.coal.mtp.repositories.PointConfigRepository;
import com.coal.mtp.service.FormService;
import com.coal.mtp.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private FormRepository formRepo;
    @Autowired
    private PointConfigRepository pointRepo;
    @Autowired
    private FormService formService;

    public ProfileDto findProfileDto(Long surfaceId, Long tunnelId) {
        // 观测点
        List<PointConfig> points = pointRepo.findByTunnelId(tunnelId);
        PointConfig origin = null;
        for (PointConfig point : points) {
            if (origin == null || origin.getZ() > point.getZ()) {
                origin = point;
            }
        }
        
        List<FormDto> dtos = new ArrayList<FormDto>();
        List<Form> forms = formRepo.findBySurfaceIdAndTunnelIdOrderByCreateTimeAsc(surfaceId, tunnelId);
        for (Form form : forms) {
            dtos.add(formService.getDto(form.getId()));
        }
        
        ProfileDto dto = new ProfileDto();
        dto.setPoints(points);
        dto.setOrigin(origin);
        dto.setForms(dtos);
        return dto;
    }
}
