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
@Table(name = "point_conf")
public class PointConfig implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id")
    @GenericGenerator(name = "snowflake", strategy = "com.coal.mtp.util.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;
	@Column(name = "tunnel_id")  
    private Long tunnelId;
    private String name;
    private Float x;
    private Float y;
    private Float z;
    private Long creator;
    @Column(name = "creator_name")    
    private String creatorName;
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime createTime;
    private boolean enable;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTunnelId() {
		return tunnelId;
	}
	public void setTunnelId(Long tunnelId) {
		this.tunnelId = tunnelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getX() {
		return x;
	}
	public void setX(Float x) {
		this.x = x;
	}
	public Float getY() {
		return y;
	}
	public void setY(Float y) {
		this.y = y;
	}
	public Float getZ() {
		return z;
	}
	public void setZ(Float z) {
		this.z = z;
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
