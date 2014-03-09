package com.coal.mtp.web;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.dto.Config;
import com.coal.mtp.dto.FormDto;
import com.coal.mtp.entity.Form;
import com.coal.mtp.service.ConfigService;
import com.coal.mtp.service.FormService;

@Controller
@RequestMapping(value = "/form")
public class FormController {
    @Autowired
    private ConfigService configService;
    @Autowired
    private FormService formService;
    
    /**
     * 配置信息
     * 
     * @param teamId
     * @return
     */
    @RequestMapping(value = "/conf", produces = "application/json")
    @ResponseBody
    public Config getConfig(@RequestParam(value = "teamId", required = false) Long teamId) {
    	Config config = configService.getConfig(teamId, true);
    	return config;
    }
    
    @RequestMapping(value = "/edit")
    public String edit(@RequestParam(value = "teamId", required = false) Long teamId, Model model) {
    	//Config config = configService.getConfig(teamId, false);
    	model.addAttribute("serverTime", new DateTime());
    	return "form-edit";
    }
    
    
    @RequestMapping(value = "/save", consumes = "application/json")
    public String save(@RequestBody @Valid FormDto dto, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		Config config = configService.getConfig(null, false);
            model.addAttribute("config", config);
            return "form-edit";
    	} else {
    		Form form = formService.create(dto);
    		return "redirect:" + form.getId() + "/submit";
    	}
    }
    
    @RequestMapping(value = "/listPage")
    public String list() {
    	return "form-list";
    }
    
    @RequestMapping(value = "/list", produces = "application/json")
    @ResponseBody
    public Page<Form> list(@PageableDefault(page =0, size = 100, sort="createTime", direction = Sort.Direction.DESC) Pageable pageable) {
    	Page<Form> forms = formService.findAll(pageable);
    	return forms;
    }
    
    @RequestMapping(value = "/{formId}", params= "view")
    public String view(@PathVariable("formId") Long formId, Model model) {
    	model.addAttribute("id", formId);
        return "form-edit";
    }
    
    @RequestMapping(value = "/{formId}", produces = "application/json")
    @ResponseBody
    public FormDto get(@PathVariable("formId") Long formId, Model model) {
    	FormDto dto = formService.getDto(formId);
        return dto;
    }
    
    @RequestMapping(value = "/{formId}/submit")
    public String submitSuccess(@PathVariable("formId") Long formId) {
    	return "form-save";
    }

}
