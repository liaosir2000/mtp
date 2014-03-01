package com.coal.mtp.service;

import com.coal.mtp.dto.FormDto;
import com.coal.mtp.entity.Form;

public interface FormService {
    
    Form create(FormDto dto);
    FormDto getDto(Long formId);
}
