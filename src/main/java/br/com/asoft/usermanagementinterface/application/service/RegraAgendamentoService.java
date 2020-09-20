package br.com.asoft.usermanagementinterface.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.application.service.generics.BasicCrudOperation;
import br.com.asoft.usermanagementinterface.model.RegraAgendamento;
import br.com.asoft.usermanagementinterface.repository.RegraAgendamentoRepository;

@Service
public class RegraAgendamentoService implements BasicCrudOperation<RegraAgendamento> {

	@Autowired
	private RegraAgendamentoRepository repository;

	@CacheEvict(value = "regraAgendamento_by_name", allEntries = true)
	public boolean save(RegraAgendamento regraAgendamento) {
		repository.save(regraAgendamento);
		return true;
	}

	public List<RegraAgendamento> getAll() {
		return (List<RegraAgendamento>) repository.findAll();
	}

	public RegraAgendamento getById(Integer regraAgendamentoId) {
		return repository.findById(regraAgendamentoId)
				.orElseThrow(() -> new ValidationException(EnumException.AREA_NAO_ENCONTRADA));
	}

	public void removeById(Integer regraAgendamentoId) {
		this.repository.deleteById(regraAgendamentoId);
	}

	public boolean delete(RegraAgendamento regraAgendamento) {
		this.repository.delete(regraAgendamento);
		return true;
	}

}