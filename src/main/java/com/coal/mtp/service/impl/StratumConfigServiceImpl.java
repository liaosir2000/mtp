package com.coal.mtp.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.StratumConfig;
import com.coal.mtp.repositories.StratumConfigRepository;
import com.coal.mtp.service.StratumConfigService;

@Service
public class StratumConfigServiceImpl implements StratumConfigService {
	@Autowired
	private StratumConfigRepository repo;

	public StratumConfig create(StratumConfig config) {
		config.setCreateTime(new DateTime());
		config.setEnable(true);
		return repo.save(config);
	}

	public List<StratumConfig> findStratums() {
		return repo.findStratums();
	}

	public void delete(Long id) {
		StratumConfig config = repo.findOne(id);
		config.setEnable(false);
		repo.save(config);
	}

}
