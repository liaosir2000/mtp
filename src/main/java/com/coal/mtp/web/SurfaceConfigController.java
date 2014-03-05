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
	
	@RequestMapping(consumes = "application/json")
	public String create(@RequestBody SurfaceConfig surface, BindingResult result) {
		if (result.hasErrors()) {
			return "surface-edit";
		} else {
			surfaceService.create(surface);
			return "redirect:surface/list";
		}
	}
	
	@RequestMapping(value = "/list", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SurfaceConfig> list(Model model) {
		List<SurfaceConfig> surfaces = surfaceService.findSurfaces();
		return surfaces;
	}
	
	@RequestMapping(value = "/{surfaceId}")
	public String delete(@PathVariable("surfaceId") Long surfaceId) {
		surfaceService.deleteSurface(surfaceId);
		return "redirect:list";
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel")
	public String createTunnel(@PathVariable("surfaceId") Long surfaceId, TunnelConfig tunnel, BindingResult result) {
		if (result.hasErrors()) {
			return "tunnel-edit";
		} else {
			tunnel.setSurfaceId(surfaceId);
			tunnelService.create(tunnel);
			return "redirect:tunnel/list";
		}
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/list")
	public String listTunnels(@PathVariable("surfaceId") Long surfaceId, Model model) {
		List<TunnelConfig> tunnels = tunnelService.findTunnels(surfaceId);
		model.addAttribute("tunnels", tunnels);
		return "tunnel-list";
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}")
	public String deleteTunnel(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId) {
		tunnelService.deleteTunnel(tunnelId);
		return "redirect:list";
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}/point")
	public String  createPoint(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId, 
			PointConfig point, BindingResult result) {
		if (result.hasErrors()) {
			return "point-edit";
		} else {
			point.setTunnelId(tunnelId);
			pointService.create(point);
			return "redirect:point/list";
		}
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}/point/list")
	public String listPoints(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId,
			Model model) {
		List<PointConfig> points = pointService.findPoints(tunnelId);
		model.addAttribute("points", points);
		return "point-list";
	}
	
	@RequestMapping(value = "/{surfaceId}/tunnel/{tunnelId}/point/{pointId}")
	public String deletePoint(@PathVariable("surfaceId") Long surfaceId, @PathVariable("tunnelId") Long tunnelId,
			@PathVariable("pointId") Long pointId) {
		pointService.deletePoint(pointId);
		return "redirect:list";
	}
}
