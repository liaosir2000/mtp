package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.PointConfig;

public interface PointConfigRepository extends CrudRepository<PointConfig, Long> {

	@Query("from PointConfig p where p.enable=true and p.tunnelId=?1 order by p.createTime desc")
	List<PointConfig> findByTunnelId(Long tunnelId);
	
	List<PointConfig> findByEnable(boolean enable);
}
