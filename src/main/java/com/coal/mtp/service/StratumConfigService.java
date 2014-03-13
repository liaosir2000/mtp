package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.entity.StratumConfig;

public interface StratumConfigService {
	
	StratumConfig create(StratumConfig surface);
	
	List<StratumConfig> findStratums();
	
	void delete(Long id);
	
}
