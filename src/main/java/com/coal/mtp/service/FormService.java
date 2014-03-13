package com.coal.mtp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.coal.mtp.dto.FormDto;
import com.coal.mtp.entity.Form;

public interface FormService {
    
    Form create(FormDto dto);
    FormDto getDto(Long formId);
    Page<Form> findAll(Pageable pageable);
}
