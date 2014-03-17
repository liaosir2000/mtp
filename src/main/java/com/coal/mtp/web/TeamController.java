package com.coal.mtp.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.dto.TeamsDto;
import com.coal.mtp.service.TeamService;

@Controller
@RequestMapping(value = "/team")
public class TeamController {
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping(value = "/init")
	public String init() {
	    return "team-init";
	}
	
	@RequestMapping(produces = "application/json") 
	@ResponseBody
	public TeamsDto getTeams() {
		return teamService.getTeams();
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("teamId") String teamId, @RequestParam("password") String paasword, 
	        HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (teamService.validate(teamId, paasword)) {
			return "redirect:/form/conf?teamId=" + teamId;
		} else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "密码错误");
			return null;
		}
	}
}
