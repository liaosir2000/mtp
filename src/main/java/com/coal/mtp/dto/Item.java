package com.coal.mtp.dto;

public class Item {

    private Long id;
    private String name;

    
    public Item() {
        super();
    }

    public Item(Long code, String name) {
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
