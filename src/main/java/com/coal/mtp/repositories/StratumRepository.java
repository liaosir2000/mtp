package com.coal.mtp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.Stratum;
import com.coal.mtp.entity.StratumLayer;

public interface StratumRepository extends CrudRepository<Stratum, Long> {

	@Query("from Stratum s where s.layer=?2 and s.formId=?1 order by s.sequence")
	List<Stratum> findStratums(Long formId, StratumLayer layer);
}
