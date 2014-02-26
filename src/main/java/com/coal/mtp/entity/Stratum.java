package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 岩层
 * 
 */
@Entity
@Table(name = "stratum")
public class Stratum implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "snowflake", strategy = "com.coal.mtp.util.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;
    @Column(name = "form_id")
    private Long formId;
    @Column(name = "dict_id")
    private Long dictId;
    @Column(name = "dict_name")
    private String dictName;
    @Column(name = "layer")
    private Integer layer;
    @Column(name = "value")
    private Float value;
    @Column(name = "sequence")
    private Integer sequence;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFormId() {
        return formId;
    }
    public void setFormId(Long formId) {
        this.formId = formId;
    }
    public Long getDictId() {
        return dictId;
    }
    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }
    public String getDictName() {
        return dictName;
    }
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    public Float getValue() {
        return value;
    }
    public void setValue(Float value) {
        this.value = value;
    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
