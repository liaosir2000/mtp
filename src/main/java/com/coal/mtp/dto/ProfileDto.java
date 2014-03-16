package com.coal.mtp.dto;

import java.util.List;

import com.coal.mtp.entity.PointConfig;

public class ProfileDto {
    
    //观测点
    private List<PointConfig> points;
    
    private List<FormDto> forms;
    //定义最小的Z为原点
    private PointConfig origin;
    public List<PointConfig> getPoints() {
        return points;
    }
    public void setPoints(List<PointConfig> points) {
        this.points = points;
    }
    public List<FormDto> getForms() {
        return forms;
    }
    public void setForms(List<FormDto> forms) {
        this.forms = forms;
    }
    public PointConfig getOrigin() {
        return origin;
    }
    public void setOrigin(PointConfig origin) {
        this.origin = origin;
    }
}
