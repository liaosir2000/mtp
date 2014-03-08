package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "form")
public class Form extends EntityId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "surface_id")
    private Long surfaceId;
    @Column(name = "surface_name")
    private String surfaceName;
    @Column(name = "create_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime createTime;
    @Column(name = "shift_id")
    private Long shiftId;
    @Column(name = "shift_name")
    private String shiftName;
    @Column(name = "tunnel_id")
    private Long tunnelId;
    @Column(name = "tunnel_name")
    private String tunnelName;
    @Column(name = "point_id")
    private Long pointId;
    @Column(name = "point_name")
    private String pointName;
    @Column(name = "point_ahead")
    private Float pointAhead;
    @Column(name = "roof_anchor")
    private Long roofAnchor;
    @Column(name = "ahead_hole")
    private Long aheadHole;
    @Column(name = "tunnel_info")
    private Long tunnelInfo;
    @Column(name = "team_id")
    private String teamId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "reporter_id")
    private String reporter;
    @Column(name = "reporter_name")
    private String reporterName;
	public Long getSurfaceId() {
		return surfaceId;
	}
	public void setSurfaceId(Long surfaceId) {
		this.surfaceId = surfaceId;
	}
	public String getSurfaceName() {
		return surfaceName;
	}
	public void setSurfaceName(String surfaceName) {
		this.surfaceName = surfaceName;
	}
	public DateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}
	public Long getShiftId() {
		return shiftId;
	}
	public void setShiftId(Long shiftId) {
		this.shiftId = shiftId;
	}
	public String getShiftName() {
		return shiftName;
	}
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	public Long getTunnelId() {
		return tunnelId;
	}
	public void setTunnelId(Long tunnelId) {
		this.tunnelId = tunnelId;
	}
	public String getTunnelName() {
		return tunnelName;
	}
	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}
	public Long getPointId() {
		return pointId;
	}
	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public Float getPointAhead() {
		return pointAhead;
	}
	public void setPointAhead(Float pointAhead) {
		this.pointAhead = pointAhead;
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
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getReporterName() {
		return reporterName;
	}
	public void setReporterName(String reporterName) {
		this.reporterName = reporterName;
	}
}
