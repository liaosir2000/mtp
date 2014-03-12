package com.coal.mtp.dto;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private Long id;
	private String name;
	private List<Item> members = new ArrayList<Item>();
	
	public Team() {
		super();
	}
	
	public Team(Long id, String name) {
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
	public List<Item> getMembers() {
		return members;
	}
	public void setMembers(List<Item> members) {
		this.members = members;
	}
	public void addMember(Item item) {
		members.add(item);
	}
}
