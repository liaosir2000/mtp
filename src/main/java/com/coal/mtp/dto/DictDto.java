package com.coal.mtp.dto;

import java.util.List;

public class DictDto {
    
    private List<DictItem> workingSurfaces;
    private List<DictItem> workShifts;
    private List<DictItem> roadways;
    private List<DictItem> observePoints;
    private List<DictItem> stratums;
    private List<DictItem> observeInfos;
    public List<DictItem> getWorkingSurfaces() {
        return workingSurfaces;
    }
    public void setWorkingSurfaces(List<DictItem> workingSurfaces) {
        this.workingSurfaces = workingSurfaces;
    }
    public List<DictItem> getWorkShifts() {
        return workShifts;
    }
    public void setWorkShifts(List<DictItem> workShifts) {
        this.workShifts = workShifts;
    }
    public List<DictItem> getRoadways() {
        return roadways;
    }
    public void setRoadways(List<DictItem> roadways) {
        this.roadways = roadways;
    }
    public List<DictItem> getObservePoints() {
        return observePoints;
    }
    public void setObservePoints(List<DictItem> observePoints) {
        this.observePoints = observePoints;
    }
    public List<DictItem> getStratums() {
        return stratums;
    }
    public void setStratums(List<DictItem> stratums) {
        this.stratums = stratums;
    }
    public List<DictItem> getObserveInfos() {
        return observeInfos;
    }
    public void setObserveInfos(List<DictItem> observeInfos) {
        this.observeInfos = observeInfos;
    }
}
