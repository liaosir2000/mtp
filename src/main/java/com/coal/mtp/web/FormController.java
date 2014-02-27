package com.coal.mtp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coal.mtp.dto.Config;
import com.coal.mtp.service.DictService;

@Controller
@RequestMapping(value = "/form")
public class FormController {
    @Autowired
    private DictService dictService;
    
    @RequestMapping(value = "/${teamId}", method = RequestMethod.GET)
    public String edit(Long teamId, Model model) {
        Config config = dictService.getConfig(teamId);
        model.addAttribute("config", config);
        return "form/edit";
    }

}
