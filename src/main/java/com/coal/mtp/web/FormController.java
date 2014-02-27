package com.coal.mtp.web;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coal.mtp.entity.Dict;
import com.coal.mtp.entity.DictType;
import com.coal.mtp.service.DictService;

@Controller
@RequestMapping(value = "/form")
public class FormController {
    @Autowired
    private DictService dictService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String edit(Model model) {
        List<Dict> surfaces = dictService.findByType(DictType.WORKING_SURFACE.toInt());
        List<Dict> shifts = dictService.findByType(DictType.WORK_SHIFT.toInt());
        List<Dict> tunnels = dictService.findByType(DictType.TUNNEL.toInt());
        List<Dict> observePoints = dictService.findByType(DictType.OBSERVE_POINT.toInt());
        List<Dict> stratums = dictService.findByType(DictType.STRATUM.toInt());
        List<Dict> observeInfos = dictService.findByType(DictType.OBSERVE_INFO.toInt());
        model.addAttribute("surfaces", surfaces);
        model.addAttribute("serverTime", new DateTime());
        model.addAttribute("shifts", shifts);
        model.addAttribute("tunnels", tunnels);
        model.addAttribute("observePoints", observePoints);
        model.addAttribute("stratums", stratums);
        model.addAttribute("observeInfos", observeInfos);
        return "form/edit";
    }

}
