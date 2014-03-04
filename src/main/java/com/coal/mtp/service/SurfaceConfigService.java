package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.entity.SurfaceConfig;

public interface SurfaceConfigService {
	
	SurfaceConfig create(SurfaceConfig surface);
	
	List<SurfaceConfig> findSurfaces();
	
	boolean deleteSurface(Long id);
	
	void update(SurfaceConfig surface);

}
