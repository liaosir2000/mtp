package com.coal.mtp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/index")
public class HomeController {

    @RequestMapping
    public String home() {
        return "surface";
    }
}
