package com.coal.mtp.dto;

import java.util.List;

import org.joda.time.DateTime;

public class Config {
    private boolean success = true;
    private String message;
    private DateTime serverTime;
    private String accessToken;
    private Team team;
    private List<Item> shifts;
    private List<Surface> surfaces;
    private List<Item> stratums;
    private List<Item> infos;
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
	public DateTime getServerTime() {
		return serverTime;
	}
	public void setServerTime(DateTime serverTime) {
		this.serverTime = serverTime;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public List<Item> getShifts() {
		return shifts;
	}
	public void setShifts(List<Item> shifts) {
		this.shifts = shifts;
	}
	public List<Surface> getSurfaces() {
		return surfaces;
	}
	public void setSurfaces(List<Surface> surfaces) {
		this.surfaces = surfaces;
	}
	public List<Item> getStratums() {
		return stratums;
	}
	public void setStratums(List<Item> stratums) {
		this.stratums = stratums;
	}
	public List<Item> getInfos() {
		return infos;
	}
	public void setInfos(List<Item> infos) {
		this.infos = infos;
	}
}
