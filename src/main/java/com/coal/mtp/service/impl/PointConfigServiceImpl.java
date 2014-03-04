package com.coal.mtp.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.PointConfig;
import com.coal.mtp.repositories.PointConfigRepository;
import com.coal.mtp.service.PointConfigService;

@Service
public class PointConfigServiceImpl implements PointConfigService {

	@Autowired
	private PointConfigRepository repo;
	
	public PointConfig create(PointConfig point) {
		point.setCreateTime(new DateTime());
		point.setEnable(true);
		return repo.save(point);
	}

	public List<PointConfig> findPoints(Long tunnelId) {
		return repo.findByTunnelId(tunnelId);
	}

	public void deletePoint(Long pointId) {
		PointConfig point = repo.findOne(pointId);
		point.setEnable(false);
		repo.save(point);
	}

}
