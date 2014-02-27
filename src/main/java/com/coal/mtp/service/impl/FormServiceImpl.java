package com.coal.mtp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.entity.Form;
import com.coal.mtp.repositories.FormRepository;
import com.coal.mtp.service.FormService;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepo;
    
    public Form create(Form form) {
        
        form = formRepo.save(form);
        return form;
    }

}
