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
@Table(name = "info_conf")
public class InfoConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "id")
    @GenericGenerator(name = "snowflake", strategy = "com.coal.mtp.util.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;
    
    private String name;
    
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
