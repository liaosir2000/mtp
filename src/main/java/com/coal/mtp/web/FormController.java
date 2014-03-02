package com.coal.mtp.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coal.mtp.dto.Config;
import com.coal.mtp.dto.FormDto;
import com.coal.mtp.entity.Form;
import com.coal.mtp.service.DictService;
import com.coal.mtp.service.FormService;

@Controller
@RequestMapping(value = "/form")
public class FormController {
    @Autowired
    private DictService dictService;
    @Autowired
    private FormService formService;
    
    @RequestMapping
    public String save(@ModelAttribute("dto") @Valid FormDto dto, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		Config config = dictService.getConfig(null, false);
            model.addAttribute("config", config);
            return "form-edit";
    	} else {
    		Form form = formService.create(dto);
    		return "redirect:form/" + form.getId() + "/submit";
    	}
    }
    
    @RequestMapping(value = "/list")
    public String list(@PageableDefault(page =0, size = 20, sort="createTime", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
    	List<Form> forms = formService.findAll(pageable);
    	model.addAttribute("forms", forms);
    	return "form-list";
    }
    
    @RequestMapping(value = "/{formId}")
    public String view(@PathVariable("formId") Long formId, Model model) {
    	Config config = dictService.getConfig(null, false);
    	FormDto dto = formService.getDto(formId);
        model.addAttribute("config", config);
        model.addAttribute("dto", dto);
        return "form-edit";
    }
    
    @RequestMapping(value = "/{formId}/submit")
    public String submitSuccess(@PathVariable("formId") Long formId) {
    	return "form-save";
    }

}
