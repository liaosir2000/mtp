package com.coal.mtp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.dto.WarnPerson;
import com.coal.mtp.entity.InfoConfig;
import com.coal.mtp.service.InfoConfigService;
import com.coal.mtp.service.TeamService;

@Controller
@RequestMapping(value = "/info")
public class InfoController {
	
	@Autowired
	private InfoConfigService service;
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping(value = "/conf")
	public String init() {
		return "info";
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void create(@RequestBody InfoConfig info) {
		service.create(info);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<InfoConfig> list() {
		return service.findInfos();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@RequestMapping(value = "/person") 
	@ResponseBody
	public List<WarnPerson> getWarnPerson() {
		List<WarnPerson> list = teamService.getWarnPersons();
		return list;
	}
}
