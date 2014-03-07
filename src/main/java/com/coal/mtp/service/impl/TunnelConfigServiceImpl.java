package com.coal.mtp.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.TunnelConfig;
import com.coal.mtp.repositories.TunnelConfigRepository;
import com.coal.mtp.service.TunnelConfigService;

@Service
public class TunnelConfigServiceImpl implements TunnelConfigService {

	@Autowired
	private TunnelConfigRepository repo;
	
	public TunnelConfig create(TunnelConfig tunnel) {
		tunnel.setCreateTime(new DateTime());
		tunnel.setEnable(true);
		return repo.save(tunnel);
	}

	public List<TunnelConfig> findTunnels(Long surfaceId) {
		return repo.findBySurfaceId(surfaceId);
	}
	
	public List<TunnelConfig> findAll() {
		return repo.findByEnable(true);
	}

	public boolean deleteTunnel(Long tunnelId) {
		TunnelConfig tunnel = repo.findOne(tunnelId);
		tunnel.setEnable(false);
		repo.save(tunnel);
		return true;
	}

}
