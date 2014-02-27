package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.coal.mtp.entity.Dict;
import com.coal.mtp.entity.DictType;

@Repository
public interface DictRepository extends PagingAndSortingRepository<Dict, Long>{
    
    @Query("from Dict d where d.enable=true and d.dictType=?1 order by d.createTime desc")
    List<Dict> findByDictType(DictType dictType);

}
