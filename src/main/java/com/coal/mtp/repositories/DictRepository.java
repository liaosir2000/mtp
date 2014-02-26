package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.coal.mtp.entity.Dict;
import com.coal.mtp.entity.DictType;

@Repository
public interface DictRepository extends PagingAndSortingRepository<Dict, Long>{
    
    List<Dict> findByDictType(DictType dictType);

}
