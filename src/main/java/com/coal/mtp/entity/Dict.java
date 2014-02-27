package com.coal.mtp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "dict")
public class Dict {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "snowflake", strategy = "com.coal.mtp.util.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;
    @Column(name = "dict_type")
    @Type(type = "com.coal.mtp.util.GenericEnumUserType", parameters = {
            @Parameter(name = "enumClass", value = "com.coal.mtp.entity.DictType"),
            @Parameter(name = "identifierMethod", value = "toInt"),
            @Parameter(name = "valueOfMethod", value = "fromInt") })
    private DictType dictType;
    private String name;
    @Column(name = "create_time")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime createTime;
    private Boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DictType getDictType() {
        return dictType;
    }

    public void setDictType(DictType type) {
        this.dictType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public DateTime getCreateTime() {
		return createTime;
	}
    
    public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

}
