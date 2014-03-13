package com.coal.mtp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.entity.ShiftConfig;
import com.coal.mtp.service.ShiftConfigService;

@Controller
@RequestMapping(value = "/shift")
public class ShiftController {
	
	@Autowired
	private ShiftConfigService service;
	
	@RequestMapping(value = "/conf")
	public String init() {
		return "shift";
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void create(@RequestBody ShiftConfig shift) {
		service.create(shift);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ShiftConfig> list() {
		return service.findShifts();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
