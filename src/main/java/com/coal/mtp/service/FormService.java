package com.coal.mtp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.coal.mtp.dto.FormDto;
import com.coal.mtp.entity.Form;

public interface FormService {
    
    Form create(FormDto dto);
    FormDto getDto(Long formId);
    List<Form> findAll(Pageable pageable);
}
