package com.coal.mtp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.coal.mtp.dto.FormDto;
import com.coal.mtp.dto.StratumDepth.Depth;
import com.coal.mtp.entity.Form;
import com.coal.mtp.entity.Stratum;
import com.coal.mtp.entity.StratumLayer;
import com.coal.mtp.repositories.FormRepository;
import com.coal.mtp.repositories.PointConfigRepository;
import com.coal.mtp.repositories.StratumRepository;
import com.coal.mtp.repositories.SurfaceConfigRepository;
import com.coal.mtp.repositories.TunnelConfigRepository;
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
	private StratumRepository stratumRepo;

	@Autowired
	private Mapper mapper;

	public Form create(FormDto dto) {
		Form form = mapper.map(dto, Form.class);
		form.setCreateTime(new DateTime());
		form.setObserverPointX(Float.parseFloat(dto.getObserverPointAhead()[0]));
		form.setObserverPointY(Float.parseFloat(dto.getObserverPointAhead()[1]));
		form.setObserverPointZ(Float.parseFloat(dto.getObserverPointAhead()[2]));
		form = buildForm(form);
		form = formRepo.save(form);
		List<Stratum> stratums = new ArrayList<Stratum>();
		int i = 0;
		for (Depth depth : dto.getStratum().getRoof()) {
			if (depth.getStratumId() != null) {
				Stratum stratum = new Stratum();
				stratum.setFormId(form.getId());
				stratum.setDictId(depth.getStratumId());
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
				stratum.setDictId(depth.getStratumId());
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
				stratum.setDictId(depth.getStratumId());
				stratum.setValue(new Float(depth.getValue()));
				stratum.setLayer(StratumLayer.LAYER_FLOOR);
				stratum.setSequence(i++);
				stratums.add(stratum);
			}
		}
		stratumRepo.save(stratums);
		return form;
	}

	public FormDto getDto(Long formId) {
		Form form = formRepo.findOne(formId);
		FormDto dto = mapper.map(form, FormDto.class);
		dto.setObserverPointAhead(new String[] {
				form.getObserverPointX().toString(),
				form.getObserverPointY().toString(),
				form.getObserverPointZ().toString() });
		List<Stratum> roofs = stratumRepo.findStratums(formId,
				StratumLayer.LAYER_ROOF);
		List<Depth> depths = convert(roofs);
		dto.getStratum().setRoof(depths);
		List<Stratum> tunnels = stratumRepo.findStratums(formId,
				StratumLayer.LAYER_TUNNEL);
		depths = convert(tunnels);
		dto.getStratum().setTunnel(depths);
		List<Stratum> floors = stratumRepo.findStratums(formId,
				StratumLayer.LAYER_FLOOR);
		depths = convert(floors);
		dto.getStratum().setFloor(depths);
		return dto;
	}

	public List<Form> findAll(Pageable pageable) {
		Page<Form> forms = formRepo.findAll(pageable);
		return forms.getContent();
	}

	private List<Depth> convert(List<Stratum> roofs) {
		List<Depth> depths = new ArrayList<Depth>();
		for (Stratum stratum : roofs) {
			Depth d = new Depth();
			d.setStratumId(stratum.getDictId());
			d.setValue(stratum.getValue().toString());
			depths.add(d);
		}
		return depths;
	}

	private Form buildForm(Form form) {
		if (form.getWorkingSurfaceId() != null) {
			form.setWorkingSurfaceName(dictService.get(
					form.getWorkingSurfaceId()).getName());
		}
		if (form.getShiftId() != null) {
			form.setShiftName(dictService.get(form.getShiftId()).getName());
		}
		if (form.getTunnelId() != null) {
			form.setTunnelName(dictService.get(form.getTunnelId()).getName());
		}
		if (form.getObserverPointId() != null) {
			form.setObservrePointName(dictService
					.get(form.getObserverPointId()).getName());
		}

		return form;
	}

}
