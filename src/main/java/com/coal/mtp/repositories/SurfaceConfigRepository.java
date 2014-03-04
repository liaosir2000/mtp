package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.SurfaceConfig;

public interface SurfaceConfigRepository extends CrudRepository<SurfaceConfig, Long> {

	@Query("from SurfaceConfig s where s.enable=true order by s.createTime desc")
	List<SurfaceConfig> findSurfaces();
}
