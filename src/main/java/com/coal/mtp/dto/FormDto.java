package com.coal.mtp.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;


public class FormDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String teamId;//队组
	private String reporter;//汇报人
	@NotNull
	private Long surfaceId;
	@NotNull
	private Long shiftId;
	@NotNull
	private Long tunnelId;
	private Long pointId;
	private Float pointAhead;
	private StratumDepth stratum = new StratumDepth();
	private Long roofAnchor;
	private Long aheadHole;
	private Long tunnelInfo;
	private List<Item> tunnels;//只在编辑时起作用
	private List<Item> points;//只在编辑时起作用
	private String reporterName;
	private String teamName;
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
	public Long getSurfaceId() {
		return surfaceId;
	}
	public void setSurfaceId(Long surfaceId) {
		this.surfaceId = surfaceId;
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
	public Long getPointId() {
		return pointId;
	}
	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}
	public Float getPointAhead() {
		return pointAhead;
	}
	public void setPointAhead(Float pointAhead) {
		this.pointAhead = pointAhead;
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
	public List<Item> getTunnels() {
		return tunnels;
	}
	public void setTunnels(List<Item> tunnels) {
		this.tunnels = tunnels;
	}
	public List<Item> getPoints() {
		return points;
	}
	public void setPoints(List<Item> points) {
		this.points = points;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	@Override
	public String toString() {
		return "FormDto [id=" + id + ", teamId=" + teamId + ", reporter="
				+ reporter + ", surfaceId=" + surfaceId + ", shiftId="
				+ shiftId + ", tunnelId=" + tunnelId + ", pointId=" + pointId
				+ ", pointAhead=" + pointAhead + ", stratum=" + stratum
				+ ", roofAnchor=" + roofAnchor + ", aheadHole=" + aheadHole
				+ ", tunnelInfo=" + tunnelInfo + ", tunnels=" + tunnels
				+ ", points=" + points + "]";
	}
}
