package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.entity.PointConfig;

public interface PointConfigService {

	PointConfig create(PointConfig point);
	
	List<PointConfig> findPoints(Long tunnelId);
	
	List<PointConfig> findAll();
	
	void deletePoint(Long pointId);
	
}
