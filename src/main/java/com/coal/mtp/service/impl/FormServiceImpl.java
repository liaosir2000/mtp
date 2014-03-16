package com.coal.mtp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coal.mtp.dto.FormDto;
import com.coal.mtp.dto.Item;
import com.coal.mtp.dto.StratumDepth.Depth;
import com.coal.mtp.email.EmailSendRequest;
import com.coal.mtp.email.EmailSenderService;
import com.coal.mtp.entity.Form;
import com.coal.mtp.entity.InfoConfig;
import com.coal.mtp.entity.PointConfig;
import com.coal.mtp.entity.Stratum;
import com.coal.mtp.entity.StratumConfig;
import com.coal.mtp.entity.StratumLayer;
import com.coal.mtp.entity.TunnelConfig;
import com.coal.mtp.entity.Warn;
import com.coal.mtp.repositories.FormRepository;
import com.coal.mtp.repositories.InfoConfigRepository;
import com.coal.mtp.repositories.PointConfigRepository;
import com.coal.mtp.repositories.ShiftConfigRepository;
import com.coal.mtp.repositories.StratumConfigRepository;
import com.coal.mtp.repositories.StratumRepository;
import com.coal.mtp.repositories.SurfaceConfigRepository;
import com.coal.mtp.repositories.TunnelConfigRepository;
import com.coal.mtp.repositories.WarnRepository;
import com.coal.mtp.service.FormService;

@Service
public class FormServiceImpl implements FormService {

	@Autowired
	private FormRepository formRepo;

	@Autowired
	private SurfaceConfigRepository surfaceCfgRepo;
	@Autowired
	private TunnelConfigRepository tunnelCfgRepo;
	@Autowired
	private PointConfigRepository pointCfgRepo;
	@Autowired
	private ShiftConfigRepository shiftCfgRepo;
	@Autowired
	private InfoConfigRepository infoCfgRepo;
	@Autowired
	private StratumConfigRepository stratumCfgRepo;

	@Autowired
	private StratumRepository stratumRepo;
	@Autowired
	private WarnRepository warnRepo;
	@Autowired
	private EmailSenderService emailService;

	@Autowired
	private Mapper mapper;
	@Value("${host:http://localhost:8080/mtp}")
	private String host;

	@Transactional
	public Form create(FormDto dto) {
		Form form = mapper.map(dto, Form.class);
		if (dto.getId() == null) {
			form.setCreateTime(new DateTime());
		} else {
			stratumRepo.deleteStratums(dto.getId());
		}
		form = buildForm(form);
		form = formRepo.save(form);
		List<Stratum> stratums = new ArrayList<Stratum>();
		int i = 0;
		for (Depth depth : dto.getStratum().getRoof()) {
			if (depth.getStratumId() != null) {
				Stratum stratum = new Stratum();
				stratum.setFormId(form.getId());
				stratum.setStratumConfigId(depth.getStratumId());
				stratum.setName(getStratumName(depth));
				stratum.setValue(new Float(depth.getValue()));
				stratum.setLayer(StratumLayer.LAYER_ROOF);
				stratum.setSequence(i++);
				stratums.add(stratum);
			}
		}
		i = 0;
		for (Depth depth : dto.getStratum().getTunnel()) {
			if (depth.getStratumId() != null) {
				Stratum stratum = new Stratum();
				stratum.setFormId(form.getId());
				stratum.setStratumConfigId(depth.getStratumId());
				stratum.setName(getStratumName(depth));
				stratum.setValue(new Float(depth.getValue()));
				stratum.setLayer(StratumLayer.LAYER_TUNNEL);
				stratum.setSequence(i++);
				stratums.add(stratum);
			}
		}
		i = 0;
		for (Depth depth : dto.getStratum().getFloor()) {
			if (depth.getStratumId() != null) {
				Stratum stratum = new Stratum();
				stratum.setFormId(form.getId());
				stratum.setStratumConfigId(depth.getStratumId());
				stratum.setName(getStratumName(depth));
				stratum.setValue(new Float(depth.getValue()));
				stratum.setLayer(StratumLayer.LAYER_FLOOR);
				stratum.setSequence(i++);
				stratums.add(stratum);
			}
		}
		stratumRepo.save(stratums);
		
		handleWarn(form, form.getRoofAnchor(), "顶板锚杆及锚索施工情况");
		handleWarn(form, form.getAheadHole(), "超前探眼情况");
		handleWarn(form, form.getTunnelInfo(), "掌子面煤岩层、瓦斯、涌水有无变化");
		return form;
	}

	public FormDto getDto(Long formId) {
		Form form = formRepo.findOne(formId);
		FormDto dto = mapper.map(form, FormDto.class);
		List<Stratum> roofs = stratumRepo.findStratums(formId, StratumLayer.LAYER_ROOF);
		List<Depth> depths = convert(roofs);
		dto.getStratum().setRoof(depths);
		List<Stratum> tunnels = stratumRepo.findStratums(formId, StratumLayer.LAYER_TUNNEL);
		depths = convert(tunnels);
		dto.getStratum().setTunnel(depths);
		List<Stratum> floors = stratumRepo.findStratums(formId, StratumLayer.LAYER_FLOOR);
		depths = convert(floors);
		dto.getStratum().setFloor(depths);
		
		dto.setTunnels(getSelectTunnels(dto.getSurfaceId()));
		dto.setPoints(getSelectPoints(dto.getTunnelId()));
		return dto;
	}
	
	public Page<Form> findAll(Pageable pageable) {
		Page<Form> forms = formRepo.findAll(pageable);
		return forms;
	}
	
	private void handleWarn(Form form, Long infoId, String title) {
		InfoConfig infoConfig = infoCfgRepo.findOne(infoId);
		if (infoConfig.isWarn()) {
			Warn warn = new Warn();
			warn.setFormId(form.getId());
			warn.setInfoId(infoId);
			warn.setInfoName(infoConfig.getName());
			warn.setPersonId(infoConfig.getPersonId());
			warn.setPersonName(infoConfig.getPersonName());
			warn.setPersonEmail(infoConfig.getPersionEmail());
			warn.setCreateTime(new DateTime());
			warn.setContent(title);
			warnRepo.save(warn);
			EmailSendRequest request = new EmailSendRequest();
			request.setRecvAddress(warn.getPersonEmail());
			request.setSubject("地址保障管理预警平台告警--" + warn.getInfoName());
			String body = "工作面：" + form.getSurfaceName() + ", 巷道:" + form.getTunnelName() + ", 观测点:" + form.getPointName();
			body = body + "。/n"+"详情见 " + host + "/form/" + form.getId() + "?view"; 
			request.setBody(body);
			emailService.send(request);
		}
	}
	
	private List<Item> getSelectTunnels(Long surfaceId) {
		List<Item> items = new ArrayList<Item>();
		List<TunnelConfig> tunnels = tunnelCfgRepo.findBySurfaceId(surfaceId);
		for (TunnelConfig tunnelConfig : tunnels) {
			items.add(mapper.map(tunnelConfig, Item.class));
		}
		return items;
	}
	
	private List<Item> getSelectPoints(Long tunnelId){
		List<Item> items = new ArrayList<Item>();
		List<PointConfig> points = pointCfgRepo.findByTunnelId(tunnelId);
		for (PointConfig pointConfig : points) {
			items.add(mapper.map(pointConfig, Item.class));
		}
		return items;
	}

	private List<Depth> convert(List<Stratum> roofs) {
		List<Depth> depths = new ArrayList<Depth>();
		for (Stratum stratum : roofs) {
			Depth d = new Depth();
			d.setStratumId(stratum.getStratumConfigId());
			d.setValue(stratum.getValue().toString());
			d.setName(stratum.getName());
			depths.add(d);
		}
		return depths;
	}
	
	private String getStratumName(Depth depth) {
		if (StringUtils.isNotBlank(depth.getName())){
			return depth.getName();
		} else {
			StratumConfig config = stratumCfgRepo.findOne(depth.getStratumId());
			return config.getName();
		}
	}

	private Form buildForm(Form form) {
		if (form.getSurfaceId() != null) {
			form.setSurfaceName(surfaceCfgRepo.findOne(form.getSurfaceId()).getName());
		}
		if (form.getShiftId() != null) {
			form.setShiftName(shiftCfgRepo.findOne(form.getShiftId()).getName());
		}
		if (form.getTunnelId() != null) {
			form.setTunnelName(tunnelCfgRepo.findOne(form.getTunnelId()).getName());
		}
		if (form.getPointId() != null) {
			form.setPointName(pointCfgRepo.findOne(form.getPointId()).getName());
		}

		return form;
	}

}
