package com.coal.mtp.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.SurfaceConfig;
import com.coal.mtp.repositories.SurfaceConfigRepository;
import com.coal.mtp.service.SurfaceConfigService;

@Service
public class SurfaceConfigServiceImpl implements SurfaceConfigService {

	@Autowired
	private SurfaceConfigRepository repo;
	public SurfaceConfig create(SurfaceConfig surface) {
		surface.setCreateTime(new DateTime());
		surface.setEnable(true);
		return repo.save(surface);
	}

	public List<SurfaceConfig> findSurfaces() {
		return repo.findSurfaces();
	}

	public boolean deleteSurface(Long id) {
		SurfaceConfig surface = repo.findOne(id);
		surface.setEnable(false);
		repo.save(surface);
		return true;
	}

	public void update(SurfaceConfig surface) {

	}

}
