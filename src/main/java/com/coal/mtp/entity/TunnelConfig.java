package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
@Entity
@Table(name = "tunnel_conf")
public class TunnelConfig extends EntityId implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(name = "surface_id")  
    private Long surfaceId;
	@NotNull
    private String name;
    private Long creator;
    @Column(name = "creator_name")    
    private String creatorName;
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(name = "create_time")    
    private DateTime createTime;
    private boolean enable;
	public Long getSurfaceId() {
		return surfaceId;
	}
	public void setSurfaceId(Long surfaceId) {
		this.surfaceId = surfaceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public DateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
