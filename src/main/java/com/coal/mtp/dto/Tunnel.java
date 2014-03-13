package com.coal.mtp.dto;

import java.util.List;

public class Tunnel {
	private Long id;
	private String name;
	private List<Item> points;
	
	public Tunnel() {
		super();
	}
	public Tunnel(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Item> getPoints() {
		return points;
	}
	public void setPoints(List<Item> points) {
		this.points = points;
	}
	
}
