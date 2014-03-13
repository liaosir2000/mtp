package com.coal.mtp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coal.mtp.dto.Config;
import com.coal.mtp.dto.Item;
import com.coal.mtp.dto.Surface;
import com.coal.mtp.dto.Team;
import com.coal.mtp.dto.Tunnel;
import com.coal.mtp.entity.InfoConfig;
import com.coal.mtp.entity.PointConfig;
import com.coal.mtp.entity.ShiftConfig;
import com.coal.mtp.entity.StratumConfig;
import com.coal.mtp.entity.SurfaceConfig;
import com.coal.mtp.entity.TunnelConfig;
import com.coal.mtp.service.AccessTokenService;
import com.coal.mtp.service.ConfigService;
import com.coal.mtp.service.InfoConfigService;
import com.coal.mtp.service.PointConfigService;
import com.coal.mtp.service.ShiftConfigService;
import com.coal.mtp.service.StratumConfigService;
import com.coal.mtp.service.SurfaceConfigService;
import com.coal.mtp.service.TunnelConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {
	@Autowired
	private SurfaceConfigService surfaceService;
	@Autowired
	private TunnelConfigService tunnelService;
	@Autowired
	private PointConfigService pointService;
	@Autowired
	private StratumConfigService stratumService;
	@Autowired
	private ShiftConfigService shiftService;
	@Autowired
	private InfoConfigService infoService;
	@Autowired
	private AccessTokenService tokenService;
	@Autowired
	private Mapper mapper;

	public Config getConfig(String teamId, boolean needToken) {
		Config config = new Config();
		config.setServerTime(new DateTime());
		if (needToken) {
			config.setAccessToken(tokenService.create().getToken());
		}
		List<StratumConfig> stratums = stratumService.findStratums();
		config.setStratums(convert(stratums));
		List<InfoConfig> infos = infoService.findInfos();
		config.setInfos(convert(infos));
		List<ShiftConfig> shifts = shiftService.findShifts();
		config.setShifts(convert(shifts));
		
		List<PointConfig> pcs = pointService.findAll();
		Map<Long, List<Item>> points = convertPoints(pcs);
		List<TunnelConfig> tcs = tunnelService.findAll();
		Map<Long, List<Tunnel>> tunnels = convertTunnels(tcs, points);
		List<SurfaceConfig> scs = surfaceService.findSurfaces();
		List<Surface> surfaces = convertSurfaces(scs, tunnels);
		config.setSurfaces(surfaces);
		config.setSuccess(true);
		//TODO
		Team team2 = new Team();
		team2.setId(2L);
		team2.setName("开拓队");
		team2.addMember(new Item(31L, "张三"));
		team2.addMember(new Item(32L, "李四"));
		config.setTeam(team2);
		return config;
	}
	
	private List<Item> convert(List<?> lists) {
		List<Item> result = new ArrayList<Item>();
		for (Object object : lists) {
			Item item = mapper.map(object, Item.class);
			result.add(item);
		}
		return result;
	}
	
	private List<Surface> convertSurfaces(List<SurfaceConfig> surfaces, Map<Long, List<Tunnel>> tunnels) {
		List<Surface> sfs = new ArrayList<Surface>();
		for (SurfaceConfig config : surfaces) {
			Surface s = mapper.map(config, Surface.class);
			s.setTunnels(tunnels.get(s.getId()));
			sfs.add(s);
		}
		return sfs;
	}
	
	private Map<Long, List<Tunnel>> convertTunnels(List<TunnelConfig> configs, Map<Long, List<Item>> points) {
		Map<Long, List<Tunnel>> result = new HashMap<Long, List<Tunnel>>();
		for (TunnelConfig config : configs) {
			List<Tunnel> items = result.get(config.getSurfaceId());
			if (items == null) {
				items = new ArrayList<Tunnel>();
				result.put(config.getSurfaceId(), items);
			}
			Tunnel tunnel = mapper.map(config, Tunnel.class);
			tunnel.setPoints(points.get(tunnel.getId()));
			items.add(tunnel);
		}
		return result;
	}
	
	private Map<Long, List<Item>> convertPoints(List<PointConfig> configs) {
		Map<Long, List<Item>> result = new HashMap<Long, List<Item>>();
		for (PointConfig config : configs) {
			List<Item> items = result.get(config.getTunnelId());
			if (items == null) {
				items = new ArrayList<Item>();
				result.put(config.getTunnelId(), items);
			}
			items.add(mapper.map(config, Item.class));
		}
		return result;
	}

}
