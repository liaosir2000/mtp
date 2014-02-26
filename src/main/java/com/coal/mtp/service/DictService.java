package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.entity.Dict;

public interface DictService {
    Dict create(Dict dict);
    List<Dict> findByType(Integer type);
    void delete(Long dictId);
}
