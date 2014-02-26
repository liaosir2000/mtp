package com.coal.mtp.entity;

public enum DictType {
    WORKING_SURFACE(1, "工作面"), 
    WORK_SHIFT(2, "轮班"), 
    ROADWAY(3, "巷道"), 
    OBSERVE_POINT(4, "观测点"), 
    STRATUM(5, "煤(岩)层"), 
    OBSERVE_INFO(6, "观测情况");

    private int value;
    private String name;

    private DictType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer toInt() {
        return value;
    }

    public static DictType fromInt(Integer value) {
        for (DictType it : DictType.values()) {
            if (it.value == value) {
                return it;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}