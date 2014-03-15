package com.coal.mtp.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bill99.base.service.IOrgAndUser;
import com.caucho.hessian.client.HessianProxyFactory;
import com.coal.mtp.dto.Team;
import com.coal.mtp.dto.TeamsDto;
import com.coal.mtp.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TeamServiceImpl implements TeamService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${mtp.console.url:http://112.124.102.24:8080/mtp-console/api.ws}")
	private String consoleUrl;
	private IOrgAndUser orgUserService;
	private ObjectMapper mapper = new ObjectMapper();
	
	public TeamsDto getTeams() {
		TeamsDto teamsDto = null;
		try {
			String orgList = orgUserService.findOrgList();
			logger.info(orgList);
			teamsDto = mapper.readValue(orgList, TeamsDto.class);
		} catch (IOException e) {
			logger.error("failed to get team list", e);
		}
//		TeamsDto dto = new TeamsDto();
//		dto.setSuccess(true);
//		Team team1 = new Team();
//		team1.setId(1L);
//		team1.setName("综掘队");
//		team1.addMember(new Item(11L, "综掘队一队"));
//		team1.addMember(new Item(12L, "综掘队二队"));
//		dto.addGroup(team1);
//		
//		Team team2 = new Team();
//		team2.setId(2L);
//		team2.setName("开拓队");
//		team2.addMember(new Item(21L, "开拓队一队"));
//		team2.addMember(new Item(22L, "开拓队二队"));
//		dto.addGroup(team2);
		return teamsDto;
	}
	
	public Team getTeam(String orgId) {
		Team team = null;
		try {
			String users = orgUserService.findUser(orgId);
			logger.info("team member:" + users);
			team = mapper.readValue(users, Team.class);
		} catch (Exception e) {
			logger.error("failed to get team member", e);
		}
		return team;
	}
	
	@PostConstruct
	public void init() {
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			orgUserService = (IOrgAndUser) factory.create(IOrgAndUser.class, consoleUrl);
		} catch (MalformedURLException e) {
			logger.error("failed to get hessian service", e);
		}
	}

	public boolean validate(String teamId, String paasword) {
		return true;
	}

}
