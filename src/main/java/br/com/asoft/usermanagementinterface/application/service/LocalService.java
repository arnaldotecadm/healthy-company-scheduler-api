package br.com.asoft.usermanagementinterface.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.application.service.generics.BasicCrudOperation;
import br.com.asoft.usermanagementinterface.model.Local;
import br.com.asoft.usermanagementinterface.repository.LocalRepository;

@Service
public class LocalService implements BasicCrudOperation<Local> {

	@Autowired
	private LocalRepository repository;

	@CacheEvict(value = "local_by_name", allEntries = true)
	public boolean save(Local local) {
		repository.save(local);
		return true;
	}

	public List<Local> getAll() {
		return (List<Local>) repository.findAll();
	}

	public Local getById(Integer localId) {
		return repository.findById(localId)
				.orElseThrow(() -> new ValidationException(EnumException.LOCAL_NAO_ENCONTRADO));
	}

	public void removeById(Integer localId) {
		this.repository.deleteById(localId);
	}

	public boolean delete(Local local) {
		this.repository.delete(local);
		return true;
	}

}