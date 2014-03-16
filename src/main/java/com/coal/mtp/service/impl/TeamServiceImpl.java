package com.coal.mtp.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bill99.base.service.IOrgAndUser;
import com.caucho.hessian.client.HessianProxyFactory;
import com.coal.mtp.dto.Team;
import com.coal.mtp.dto.TeamsDto;
import com.coal.mtp.dto.WarnPerson;
import com.coal.mtp.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

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
		return teamsDto;
	}
	
	public Team getTeam(String orgId) {
		Team team = null;
		try {
			String users = orgUserService.findUser(orgId);
			logger.info("team member:" + users);
			if (StringUtils.isNotBlank(users)) {
			    team = mapper.readValue(users, Team.class);
			}
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
		boolean valid = false;
		try {
			valid = orgUserService.validate(teamId, paasword);
		} catch (Exception e) {
			logger.error("failed to validate team password", e);
		}
		return valid;
	}
	
	public List<WarnPerson> getWarnPersons() {
		List<WarnPerson> ps = new ArrayList<WarnPerson>();
		String json = orgUserService.findUserList();
		logger.info("warn person list:{}", json);
		CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, WarnPerson.class);
		try {
			ps = mapper.readValue(json, type);
		} catch (Exception e) {
			logger.error("failed to get warn person list", e);
		}
		return ps;
	}

}
