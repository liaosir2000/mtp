package com.coal.mtp.service.impl;

import org.springframework.stereotype.Service;

import com.coal.mtp.dto.Item;
import com.coal.mtp.dto.Team;
import com.coal.mtp.dto.TeamsDto;
import com.coal.mtp.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	public TeamsDto getTeams() {
		TeamsDto dto = new TeamsDto();
		dto.setSuccess(true);
		Team team1 = new Team();
		team1.setId(1L);
		team1.setName("综掘队");
		team1.addMember(new Item(11L, "综掘队一队"));
		team1.addMember(new Item(12L, "综掘队二队"));
		dto.addGroup(team1);
		
		Team team2 = new Team();
		team2.setId(2L);
		team2.setName("开拓队");
		team2.addMember(new Item(21L, "开拓队一队"));
		team2.addMember(new Item(22L, "开拓队二队"));
		dto.addGroup(team2);
		return dto;
	}

	public boolean validate(String teamId, String paasword) {
		return true;
	}

}
