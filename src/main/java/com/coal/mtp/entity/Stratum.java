package com.coal.mtp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

/**
 * 岩层
 * 
 */
@Entity
@Table(name = "stratum")
public class Stratum extends EntityId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "form_id")
    private Long formId;
    @Column(name = "stratum_conf_id")
    private Long stratumConfigId;
    @Column(name = "name")
    private String name;
    @Column(name = "layer")
    @Type(type = "com.coal.mtp.util.GenericEnumUserType", parameters = {
            @Parameter(name = "enumClass", value = "com.coal.mtp.entity.StratumLayer"),
            @Parameter(name = "identifierMethod", value = "toInt"),
            @Parameter(name = "valueOfMethod", value = "fromInt") })
    private StratumLayer layer;
    @Column(name = "value")
    private Float value;
    @Column(name = "sequence")
    private Integer sequence;
    public Long getFormId() {
        return formId;
    }
    public void setFormId(Long formId) {
        this.formId = formId;
    }
    public Long getStratumConfigId() {
        return stratumConfigId;
    }
    public void setStratumConfigId(Long dictId) {
        this.stratumConfigId = dictId;
    }
    public String getName() {
        return name;
    }
    public void setName(String dictName) {
        this.name = dictName;
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
    public StratumLayer getLayer() {
		return layer;
	}
    public void setLayer(StratumLayer layer) {
		this.layer = layer;
	}
}
