package com.coal.mtp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeamsDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private List<Team> groups = new ArrayList<Team>();
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Team> getGroups() {
		return groups;
	}
	public void setGroups(List<Team> groups) {
		this.groups = groups;
	}
	public void addGroup(Team team) {
		groups.add(team);
	}
}
