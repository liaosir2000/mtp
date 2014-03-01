package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "form")
public class Form implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "snowflake", strategy = "com.coal.mtp.util.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;
    @Column(name = "surface_id")
    private Long workingSurfaceId;
    @Column(name = "surface_name")
    private String workingSurfaceName;
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
    @Column(name = "observer_point_id")
    private Long observerPointId;
    @Column(name = "observer_point_name")
    private String observrePointName;
    @Column(name = "observer_point_x")
    private Float observerPointX;
    @Column(name = "observer_point_y")
    private Float observerPointY;
    @Column(name = "observer_point_z")
    private Float observerPointZ;
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
    private String reporterId;
    @Column(name = "reporter")
    private String reporter;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWorkingSurfaceId() {
		return workingSurfaceId;
	}
	public void setWorkingSurfaceId(Long workingSurfaceId) {
		this.workingSurfaceId = workingSurfaceId;
	}
	public String getWorkingSurfaceName() {
		return workingSurfaceName;
	}
	public void setWorkingSurfaceName(String workingSurfaceName) {
		this.workingSurfaceName = workingSurfaceName;
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
	public Long getObserverPointId() {
		return observerPointId;
	}
	public void setObserverPointId(Long observerPointId) {
		this.observerPointId = observerPointId;
	}
	public String getObservrePointName() {
		return observrePointName;
	}
	public void setObservrePointName(String observrePointName) {
		this.observrePointName = observrePointName;
	}
	public Float getObserverPointX() {
		return observerPointX;
	}
	public void setObserverPointX(Float observerPointX) {
		this.observerPointX = observerPointX;
	}
	public Float getObserverPointY() {
		return observerPointY;
	}
	public void setObserverPointY(Float observerPointY) {
		this.observerPointY = observerPointY;
	}
	public Float getObserverPointZ() {
		return observerPointZ;
	}
	public void setObserverPointZ(Float observerPointZ) {
		this.observerPointZ = observerPointZ;
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
	public String getReporterId() {
		return reporterId;
	}
	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
}
