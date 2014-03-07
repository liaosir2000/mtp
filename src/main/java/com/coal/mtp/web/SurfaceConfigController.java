package com.coal.mtp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coal.mtp.entity.PointConfig;
import com.coal.mtp.entity.SurfaceConfig;
import com.coal.mtp.entity.TunnelConfig;
import com.coal.mtp.service.PointConfigService;
import com.coal.mtp.service.SurfaceConfigService;
import com.coal.mtp.service.TunnelConfigService;

@Controller
@RequestMapping("/surface")
public class SurfaceConfigController {

	@Autowired
	private SurfaceConfigService surfaceService;
	
	@Autowired
	private TunnelConfigService tunnelService;
	
	@Autowired
	private PointConfigService pointService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void create(@RequestBody SurfaceConfig surface, BindingResult result) {
		surfaceService.create(surface);
	}
	
	@RequestMapping(produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SurfaceConfig> list(Model model) {
		List<SurfaceConfig> surfaces = surfaceService.findSurfaces();
		return surfaces;
	}
	
	@RequestMapping(value = "/{surfaceId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("surfaceId") Long surfaceId) {
		surfaceService.deleteSurface(surfaceId);
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel", method = RequestMethod.POST)
	@ResponseBody
	public void createTunnel(@PathVariable("surfaceId") Long surfaceId, @RequestBody TunnelConfig tunnel, BindingResult result) {
		tunnel.setSurfaceId(surfaceId);
		tunnelService.create(tunnel);
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel", method = RequestMethod.GET)
	@ResponseBody
	public List<TunnelConfig> listTunnels(@PathVariable("surfaceId") Long surfaceId, Model model) {
		List<TunnelConfig> tunnels = tunnelService.findTunnels(surfaceId);
		return tunnels;
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteTunnel(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId) {
		tunnelService.deleteTunnel(tunnelId);
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}/point", method = RequestMethod.POST)
	@ResponseBody
	public void  createPoint(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId, 
	        @RequestBody PointConfig point, BindingResult result) {
		point.setTunnelId(tunnelId);
		pointService.create(point);
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}/point", method = RequestMethod.GET)
	@ResponseBody
	public List<PointConfig> listPoints(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId) {
		List<PointConfig> points = pointService.findPoints(tunnelId);
		return points;
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}/point/{pointId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deletePoint(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId,
			@PathVariable("pointId") Long pointId) {
		pointService.deletePoint(pointId);
	}
}
