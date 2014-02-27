package com.coal.mtp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.dto.DictDto;
import com.coal.mtp.dto.DictItem;
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
		List<Dict> dicts = dictService.findByType(type);
    	model.addAttribute("dicts", dicts);
    	model.addAttribute("type", DictType.fromInt(type).getName());
    	return "dict/list";
    }
    
    @RequestMapping(value = "/{type}/{id}", params = "delete")
    public String deleteDict(@PathVariable("type") Integer type, @PathVariable("id") Long id) {
        dictService.delete(id);
        return "redirect:../"+type;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public DictDto getAllDict() {
        DictDto dict = new DictDto();
        dict.setWorkingSurfaces(getDictItems(DictType.WORKING_SURFACE));
        dict.setWorkShifts(getDictItems(DictType.WORK_SHIFT));
        dict.setRoadways(getDictItems(DictType.TUNNEL));
        dict.setObservePoints(getDictItems(DictType.OBSERVE_POINT));
        dict.setObserveInfos(getDictItems(DictType.OBSERVE_INFO));
        return dict;
    }
    
    private List<DictItem> getDictItems(DictType type) {
        List<DictItem> dictItems = new ArrayList<DictItem>();
        List<Dict> dicts = dictService.findByType(type.toInt());
        for (Dict dict : dicts) {
            dictItems.add(new DictItem(dict.getId(),dict.getName()));
        }
        return dictItems;
    }

}
