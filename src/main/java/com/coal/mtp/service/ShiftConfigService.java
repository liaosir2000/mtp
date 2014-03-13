package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.entity.ShiftConfig;

public interface ShiftConfigService {

	ShiftConfig create(ShiftConfig shift);
	
	List<ShiftConfig> findShifts();
	
	void delete(Long id);
}
