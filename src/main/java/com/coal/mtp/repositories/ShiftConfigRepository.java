package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.ShiftConfig;

public interface ShiftConfigRepository extends CrudRepository<ShiftConfig, Long> {

	@Query("from ShiftConfig s where s.enable=true order by s.createTime desc")
	List<ShiftConfig> findShifts();
}
