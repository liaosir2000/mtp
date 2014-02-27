package com.coal.mtp.dto;

import java.util.List;

import org.joda.time.DateTime;

public class Config {
    private boolean success = true;
    private String message;
    private DateTime serverTime;
    private String accessToken;
    private DictItem team;
    private List<DictItem> shifts;
    private List<DictItem> workingSurfaces;
    private List<DictItem> tunnels;
    private List<DictItem> observerPoints;
    private List<DictItem> stratums;
    private List<DictItem> observerInfos;
    private List<DictItem> teamMembers;
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
    public DictItem getTeam() {
        return team;
    }
    public void setTeam(DictItem team) {
        this.team = team;
    }
    public List<DictItem> getShifts() {
        return shifts;
    }
    public void setShifts(List<DictItem> shifts) {
        this.shifts = shifts;
    }
    public List<DictItem> getWorkingSurfaces() {
        return workingSurfaces;
    }
    public void setWorkingSurfaces(List<DictItem> workingSurfaces) {
        this.workingSurfaces = workingSurfaces;
    }
    public List<DictItem> getTunnels() {
        return tunnels;
    }
    public void setTunnels(List<DictItem> tunnels) {
        this.tunnels = tunnels;
    }
    public List<DictItem> getObserverPoints() {
        return observerPoints;
    }
    public void setObserverPoints(List<DictItem> observerPoints) {
        this.observerPoints = observerPoints;
    }
    public List<DictItem> getStratums() {
        return stratums;
    }
    public void setStratums(List<DictItem> stratums) {
        this.stratums = stratums;
    }
    public List<DictItem> getObserverInfos() {
        return observerInfos;
    }
    public void setObserverInfos(List<DictItem> observerInfos) {
        this.observerInfos = observerInfos;
    }
    public List<DictItem> getTeamMembers() {
        return teamMembers;
    }
    public void setTeamMembers(List<DictItem> teamMembers) {
        this.teamMembers = teamMembers;
    }
}
