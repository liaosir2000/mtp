package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.entity.InfoConfig;

public interface InfoConfigService {

	InfoConfig create(InfoConfig Info);
	
	List<InfoConfig> findInfos();
	
	void delete(Long id);
}
