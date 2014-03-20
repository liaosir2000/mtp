package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "access_token")
public class AccessToken extends EntityId implements Serializable{
    private static final long serialVersionUID = 1L;
    @Column(name = "token")
    private String token;
    @Column(name = "create_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime createTime;
    @Column(name = "last_access_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime lastAccessTime;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public DateTime getCreateTime() {
        return createTime;
    }
    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }
    public DateTime getLastAccessTime() {
        return lastAccessTime;
    }
    public void setLastAccessTime(DateTime lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
}
