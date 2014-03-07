package com.coal.mtp.dto;

import java.util.List;

public class Surface {
	private Long id;
	private String name;
	private List<Tunnel> tunnels;
	
	public Surface() {
		super();
	}
	
	public Surface(Long id, String name) {
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
	public List<Tunnel> getTunnels() {
		return tunnels;
	}
	public void setTunnels(List<Tunnel> tunnels) {
		this.tunnels = tunnels;
	}
	
}
