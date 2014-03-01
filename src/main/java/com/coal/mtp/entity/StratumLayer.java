package com.coal.mtp.entity;

public enum StratumLayer {
    LAYER_ROOF(1, "顶部"), 
    LAYER_TUNNEL(2, "掌子面"), 
    LAYER_FLOOR(3, "底部");

    private int value;
    private String name;

    private StratumLayer(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer toInt() {
        return value;
    }

    public static StratumLayer fromInt(Integer value) {
        for (StratumLayer it : StratumLayer.values()) {
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