package com.coal.mtp.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;


public class FormDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String teamId;//队组
	private String reporter;//汇报人
	@NotNull
	private Long workingSurfaceId;
	@NotNull
	private Long shiftId;
	@NotNull
	private Long tunnelId;
	private Long observerPointId;
	private String[] observerPointAhead = new String[3];//x,y,z
	private StratumDepth stratum = new StratumDepth();
	private Long roofAnchor;
	private Long aheadHole;
	private Long tunnelInfo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public Long getWorkingSurfaceId() {
		return workingSurfaceId;
	}
	public void setWorkingSurfaceId(Long workingSurfaceId) {
		this.workingSurfaceId = workingSurfaceId;
	}
	public Long getShiftId() {
		return shiftId;
	}
	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}
	public Long getTunnelId() {
		return tunnelId;
	}
	public void setTunnelId(Long tunnelId) {
		this.tunnelId = tunnelId;
	}
	public Long getObserverPointId() {
		return observerPointId;
	}
	public void setObserverPointId(Long observerPointId) {
		this.observerPointId = observerPointId;
	}
	public String[] getObserverPointAhead() {
		return observerPointAhead;
	}
	public void setObserverPointAhead(String[] observerPointAhead) {
		this.observerPointAhead = observerPointAhead;
	}
	public StratumDepth getStratum() {
		return stratum;
	}
	public void setStratum(StratumDepth stratum) {
		this.stratum = stratum;
	}
	public Long getRoofAnchor() {
		return roofAnchor;
	}
	public void setRoofAnchor(Long roofAnchor) {
		this.roofAnchor = roofAnchor;
	}
	public Long getAheadHole() {
		return aheadHole;
	}
	public void setAheadHole(Long aheadHole) {
		this.aheadHole = aheadHole;
	}
	public Long getTunnelInfo() {
		return tunnelInfo;
	}
	public void setTunnelInfo(Long tunnelInfo) {
		this.tunnelInfo = tunnelInfo;
	}
}
