package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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
    private Long workingSurfaceId;
    private String workingSurfaceName;
    private DateTime createTime;
    private Long shiftId;
    private String shiftName;
    private Long tunnelId;
    private String tunnelName;
    private Long observerPointId;
    private String observrePointName;
    private Long observerInfo1;
    private Long observerInfo2;
    private Long observerInfo3;
    private String teamId;
    private String teamName;
    private String reporterId;
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
    public Long getObserverInfo1() {
        return observerInfo1;
    }
    public void setObserverInfo1(Long observerInfo1) {
        this.observerInfo1 = observerInfo1;
    }
    public Long getObserverInfo2() {
        return observerInfo2;
    }
    public void setObserverInfo2(Long observerInfo2) {
        this.observerInfo2 = observerInfo2;
    }
    public Long getObserverInfo3() {
        return observerInfo3;
    }
    public void setObserverInfo3(Long observerInfo3) {
        this.observerInfo3 = observerInfo3;
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
