package com.coal.mtp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.dto.Config;
import com.coal.mtp.entity.Dict;
import com.coal.mtp.entity.DictType;
import com.coal.mtp.service.DictService;

@Controller
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;
    
    @RequestMapping(value="/{type}", method = RequestMethod.POST)
    public String createDictItem(@PathVariable("type") Integer type, @RequestParam("name") String name){
    	Dict dict = new Dict();
    	dict.setDictType(DictType.fromInt(type));
    	dict.setName(name);
    	dictService.create(dict);
    	return "redirect:" + type;
    }
    
    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public String listDictByType(@PathVariable("type") Integer type, Model model) {
        DictType dictType = DictType.fromInt(type);
		List<Dict> dicts = dictService.findByType(dictType);
    	model.addAttribute("dicts", dicts);
    	model.addAttribute("type", dictType.getName());
    	return "dict-list";
    }
    
    @RequestMapping(value = "/{type}/{id}", params = "delete")
    public String deleteDict(@PathVariable("type") Integer type, @PathVariable("id") Long id) {
        dictService.delete(id);
        return "redirect:../"+type;
    }

    @RequestMapping(value = {"/conf", "/conf/{teamId}"}, method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Config getAllDict(@PathVariable("teamId") Long teamId) {
        Config config = dictService.getConfig(teamId, true);
        return config;
    }
}
