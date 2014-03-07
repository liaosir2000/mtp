package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.TunnelConfig;

public interface TunnelConfigRepository extends CrudRepository<TunnelConfig, Long> {

	@Query("from TunnelConfig t where t.enable=true and t.surfaceId=?1 order by t.createTime desc")
	List<TunnelConfig> findBySurfaceId(Long surfaceId);

	List<TunnelConfig> findByEnable(boolean b);
}
