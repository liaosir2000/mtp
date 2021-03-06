package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.entity.TunnelConfig;

public interface TunnelConfigService {
	
	TunnelConfig create(TunnelConfig tunnel);
	
	List<TunnelConfig> findTunnels(Long surfaceId);
	
	List<TunnelConfig> findAll();
	
	boolean deleteTunnel(Long tunnelId);

}
