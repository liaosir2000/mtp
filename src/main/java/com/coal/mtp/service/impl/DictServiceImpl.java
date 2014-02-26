package com.coal.mtp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coal.mtp.entity.Dict;
import com.coal.mtp.entity.DictType;
import com.coal.mtp.repositories.DictRepository;
import com.coal.mtp.service.DictService;

@Service
public class DictServiceImpl implements DictService {

    @Autowired
    private DictRepository dictRepo;
    
    @Transactional
    public Dict create(Dict dict) {
        dict = dictRepo.save(dict);
        return dict;
    }

    public List<Dict> findByType(Integer type) {
        List<Dict> dicts = dictRepo.findByDictType(DictType.fromInt(type));
        return dicts;
    }
    
    public void delete(Long dictId) {
        Dict dict = dictRepo.findOne(dictId);
        dict.setEnable(false);
        dictRepo.save(dict);
    }

}
