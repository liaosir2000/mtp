package com.coal.mtp.service;

import java.util.List;

import com.coal.mtp.dto.Team;
import com.coal.mtp.dto.TeamsDto;
import com.coal.mtp.dto.WarnPerson;

public interface TeamService {
	
	TeamsDto getTeams();
	
	boolean validate(String teamId, String paasword);
	
	Team getTeam(String orgId);
	
	List<WarnPerson> getWarnPersons();

}
