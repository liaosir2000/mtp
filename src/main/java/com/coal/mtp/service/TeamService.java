package com.coal.mtp.service;

import com.coal.mtp.dto.TeamsDto;

public interface TeamService {
	
	TeamsDto getTeams();
	
	boolean validate(String teamId, String paasword);

}
