package com.coal.mtp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manage")
public class ManageController {
    
    @RequestMapping
    public String index() {
        return "manage/index";
    }

}
