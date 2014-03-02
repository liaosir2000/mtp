package com.coal.mtp.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.coal.mtp.entity.Form;

public interface FormRepository extends CrudRepository<Form, Long> {

	Page<Form> findAll(Pageable pageable);
}
