package com.coal.mtp.service.impl;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.InfoConfig;
import com.coal.mtp.repositories.InfoConfigRepository;
import com.coal.mtp.service.InfoConfigService;

@Service
public class InfoConfigServiceImpl implements InfoConfigService {

	@Autowired
	private InfoConfigRepository repo;
	
	public InfoConfig create(InfoConfig info) {
		info.setCreateTime(new DateTime());
		info.setEnable(true);
		return repo.save(info);
	}

	public List<InfoConfig> findInfos() {
		return repo.findInfos();
	}

	public void delete(Long id) {
		InfoConfig info = repo.findOne(id);
		info.setEnable(false);
		repo.save(info);
	}

}
