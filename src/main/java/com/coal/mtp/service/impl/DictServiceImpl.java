package com.coal.mtp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coal.mtp.dto.Config;
import com.coal.mtp.dto.DictItem;
import com.coal.mtp.entity.AccessToken;
import com.coal.mtp.entity.Dict;
import com.coal.mtp.entity.DictType;
import com.coal.mtp.repositories.DictRepository;
import com.coal.mtp.service.AccessTokenService;
import com.coal.mtp.service.DictService;

@Service
public class DictServiceImpl implements DictService {
    
    @Autowired
    private AccessTokenService accessTokenService;

    @Autowired
    private DictRepository dictRepo;
    
    @Transactional
    public Dict create(Dict dict) {
    	dict.setCreateTime(new DateTime());
    	dict.setEnable(true);
        dict = dictRepo.save(dict);
        return dict;
    }

    public List<Dict> findByType(DictType type) {
        List<Dict> dicts = dictRepo.findByDictType(type);
        return dicts;
    }
    
    public void delete(Long dictId) {
        Dict dict = dictRepo.findOne(dictId);
        dict.setEnable(false);
        dictRepo.save(dict);
    }
    
    public Config getConfig(Long teamId) {
        Config config = new Config();
        AccessToken accessToken = accessTokenService.create();
        config.setAccessToken(accessToken.getToken());
        config.setServerTime(new DateTime());
        config.setWorkingSurfaces(getDictItems(DictType.WORKING_SURFACE));
        config.setShifts(getDictItems(DictType.WORK_SHIFT));
        config.setTunnels(getDictItems(DictType.STRATUM));
        config.setObserverPoints(getDictItems(DictType.OBSERVE_POINT));
        config.setStratums(getDictItems(DictType.STRATUM));
        config.setObserverInfos(getDictItems(DictType.OBSERVE_INFO));
        //TODO team info
        return config;
    }

    private List<DictItem> getDictItems(DictType type) {
        List<DictItem> dictItems = new ArrayList<DictItem>();
        List<Dict> dicts = findByType(type);
        for (Dict dict : dicts) {
            dictItems.add(new DictItem(dict.getId(),dict.getName()));
        }
        return dictItems;
    }
}
