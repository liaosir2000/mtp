package com.coal.mtp.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.ShiftConfig;
import com.coal.mtp.repositories.ShiftConfigRepository;
import com.coal.mtp.service.ShiftConfigService;

@Service
public class ShiftConfigServiceImpl implements ShiftConfigService {

	@Autowired
	private ShiftConfigRepository repo;
	
	public ShiftConfig create(ShiftConfig shift) {
		shift.setCreateTime(new DateTime());
		shift.setEnable(true);
		return repo.save(shift);
	}

	public List<ShiftConfig> findShifts() {
		return repo.findShifts();
	}

	public void delete(Long id) {
		ShiftConfig shift = repo.findOne(id);
		shift.setEnable(false);
		repo.save(shift);
	}

}
