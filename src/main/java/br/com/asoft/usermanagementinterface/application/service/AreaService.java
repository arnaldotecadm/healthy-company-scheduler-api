package br.com.asoft.usermanagementinterface.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.application.service.generics.BasicCrudOperation;
import br.com.asoft.usermanagementinterface.model.Area;
import br.com.asoft.usermanagementinterface.repository.AreaRepository;

@Service
public class AreaService implements BasicCrudOperation<Area> {

	@Autowired
	private AreaRepository repository;

	@CacheEvict(value = "area_by_name", allEntries = true)
	public boolean save(Area area) {
		repository.save(area);
		return true;
	}

	public List<Area> getAll() {
		return (List<Area>) repository.findAll();
	}

	public Area getById(Integer areaId) {
		return repository.findById(areaId)
				.orElseThrow(() -> new ValidationException(EnumException.AREA_NAO_ENCONTRADA));
	}

	public void removeById(Integer areaId) {
		this.repository.deleteById(areaId);
	}

	public boolean delete(Area area) {
		this.repository.delete(area);
		return true;
	}

}