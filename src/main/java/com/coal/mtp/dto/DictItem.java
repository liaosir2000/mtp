package com.coal.mtp.dto;

public class DictItem {

    private Long id;
    private String name;

    
    public DictItem() {
        super();
    }

    public DictItem(Long code, String name) {
        super();
        this.id = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long code) {
        this.id = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
