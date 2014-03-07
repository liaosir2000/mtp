package com.coal.mtp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.entity.StratumConfig;
import com.coal.mtp.service.StratumConfigService;

@Controller
@RequestMapping(value = "/stratum")
public class StratumController {
	
	@Autowired
	private StratumConfigService service;
	
	@RequestMapping(value = "/conf")
	public String init() {
		return "stratum";
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void create(@RequestBody StratumConfig stratum) {
		service.create(stratum);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<StratumConfig> list() {
		return service.findStratums();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
