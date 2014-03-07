package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.InfoConfig;

public interface InfoConfigRepository extends CrudRepository<InfoConfig, Long> {

	@Query("from InfoConfig c where c.enable=true order by c.createTime desc")
	List<InfoConfig> findInfos();
}
