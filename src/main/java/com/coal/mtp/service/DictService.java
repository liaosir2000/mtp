package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.dto.Config;
import com.coal.mtp.entity.Dict;
import com.coal.mtp.entity.DictType;

public interface DictService {
    Dict create(Dict dict);
    List<Dict> findByType(DictType type);
    void delete(Long dictId);
    Config getConfig(Long teamId);
    Dict get(Long id);
}
