package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.StratumConfig;

public interface StratumConfigRepository extends CrudRepository<StratumConfig, Long> {
	@Query("from StratumConfig s where s.enable=true order by s.id desc")
	List<StratumConfig> findStratums();
}
