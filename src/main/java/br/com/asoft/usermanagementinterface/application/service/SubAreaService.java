package br.com.asoft.usermanagementinterface.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.application.service.generics.BasicCrudOperation;
import br.com.asoft.usermanagementinterface.model.SubArea;
import br.com.asoft.usermanagementinterface.repository.SubAreaRepository;

@Service
public class SubAreaService implements BasicCrudOperation<SubArea> {

	@Autowired
	private SubAreaRepository repository;

	@CacheEvict(value = "subArea_by_name", allEntries = true)
	public boolean save(SubArea subArea) {
		repository.save(subArea);
		return true;
	}

	public List<SubArea> getAll() {
		return (List<SubArea>) repository.findAll();
	}

	public SubArea getById(Integer subAreaId) {
		return repository.findById(subAreaId)
				.orElseThrow(() -> new ValidationException(EnumException.SUB_AREA_NAO_ENCONTRADA));
	}

	public void removeById(Integer subAreaId) {
		this.repository.deleteById(subAreaId);
	}

	public boolean delete(SubArea subArea) {
		this.repository.delete(subArea);
		return true;
	}

}