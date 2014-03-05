package com.coal.mtp.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 煤岩层厚度
 *
 */
public class StratumDepth implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Depth> roof;//顶部
	private List<Depth> tunnel;//掌子面
	private List<Depth> floor;//底部

	public List<Depth> getRoof() {
		return roof;
	}

	public void setRoof(List<Depth> roof) {
		this.roof = roof;
	}

	public List<Depth> getTunnel() {
		return tunnel;
	}

	public void setTunnel(List<Depth> tunnel) {
		this.tunnel = tunnel;
	}

	public List<Depth> getFloor() {
		return floor;
	}

	public void setFloor(List<Depth> floor) {
		this.floor = floor;
	}

	public static class Depth {
		private Long stratumId;//岩层类型
		private String value;//厚度
		public Long getStratumId() {
			return stratumId;
		}
		public void setStratumId(Long stratumId) {
			this.stratumId = stratumId;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
