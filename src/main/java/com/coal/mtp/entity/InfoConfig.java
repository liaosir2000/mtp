package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "info_conf")
public class InfoConfig extends EntityId implements Serializable {
	private static final long serialVersionUID = 1L;
    
    private String name;
    
    private Long creator;
    @Column(name = "creator_name")    
    private String creatorName;
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(name = "create_time")
    private DateTime createTime;
    private boolean enable;
    private boolean warn;
    @Column(name = "person_id")
    private String personId;
    @Column(name = "person_name")
    private String personName;
    @Column(name = "create_email")
    private String persionEmail;
    
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
	public boolean isWarn() {
		return warn;
	}
	public void setWarn(boolean warn) {
		this.warn = warn;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersionEmail() {
		return persionEmail;
	}
	public void setPersionEmail(String persionEmail) {
		this.persionEmail = persionEmail;
	}
}
