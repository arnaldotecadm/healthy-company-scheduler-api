package br.com.asoft.usermanagementinterface.application.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.application.service.generics.BasicCrudOperation;
import br.com.asoft.usermanagementinterface.model.Agendamento;
import br.com.asoft.usermanagementinterface.model.Area;
import br.com.asoft.usermanagementinterface.model.Local;
import br.com.asoft.usermanagementinterface.model.SubArea;
import br.com.asoft.usermanagementinterface.model.Usuario;
import br.com.asoft.usermanagementinterface.model.dto.AgendamentoDTO;
import br.com.asoft.usermanagementinterface.repository.AgendamentoRepository;
import br.com.asoft.usermanagementinterface.repository.AreaRepository;
import br.com.asoft.usermanagementinterface.repository.LocalRepository;
import br.com.asoft.usermanagementinterface.repository.SubAreaRepository;
import br.com.asoft.usermanagementinterface.repository.UsuarioRepository;

@Service
public class AgendamentoService implements BasicCrudOperation<Agendamento> {

	@Autowired
	private AgendamentoRepository repository;

	@Autowired
	private UsuarioRepository usuarioRepo;

	@Autowired
	private LocalRepository localRepo;

	@Autowired
	private AreaRepository areaRepo;

	@Autowired
	private SubAreaRepository subAreaRepo;

	@CacheEvict(value = "agendamento_by_username", allEntries = true)
	public boolean save(Agendamento agendamento) {
		repository.save(agendamento);
		return true;
	}

	public List<Agendamento> getAll() {
		return (List<Agendamento>) repository.findAll();
	}

	public Agendamento getById(Integer agendamentoId) {
		return repository.findById(agendamentoId)
				.orElseThrow(() -> new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
	}

	public void removeById(Integer agendamentoId) {
		this.repository.deleteById(agendamentoId);
	}

	public boolean delete(Agendamento agendamento) {
		this.repository.delete(agendamento);
		return true;
	}

	public Agendamento getByUsuarioAndDataAgendamento(Usuario usuario, Calendar dataAgendamento) {
		return this.repository.getByUsuarioAndDataAgendamento(usuario, dataAgendamento);
	}

	public List<Agendamento> getByDataAgendamento(Calendar dataAgendamento) {
		return this.repository.getByDataAgendamento(dataAgendamento);
	}

	public Agendamento convertDTOToModel(AgendamentoDTO aDto) {
		Agendamento model = new Agendamento();
		if (null == aDto) {
			return model;
		}

		/**
		 * No caso de edicao recupera os id do banco
		 */
		if (null != aDto.getId()) {
			Optional<Agendamento> aById = this.repository.findById(aDto.getId());
			if (aById.isPresent()) {
				model.setId(aById.get().getId());
			}
		}

		/**
		 * preenche da data de agendamento
		 */
		model.setDataAgendamento(aDto.getDataAgendamento());

		/**
		 * busca as informacoes de usuario
		 */
		if (null != aDto.getIdFuncionario()) {
			Optional<Usuario> uById = this.usuarioRepo.findById(aDto.getIdFuncionario());
			if (uById.isPresent()) {
				model.setUsuario(uById.get());
			}
		}

		/**
		 * busca as informacoes de local do banco
		 */
		if (null != aDto.getLocal()) {
			Optional<Local> lById = this.localRepo.findById(aDto.getLocal());
			if (lById.isPresent()) {
				model.setLocal(lById.get());
			}
		}

		/**
		 * busca as informacoes de area do banco
		 */
		if (null != aDto.getArea()) {
			Optional<Area> aById = this.areaRepo.findById(aDto.getArea());
			if (aById.isPresent()) {
				model.setArea(aById.get());
			}
		}

		/**
		 * busca as informacoes de subarea do banco
		 */
		if (null != aDto.getSubArea()) {
			Optional<SubArea> saById = this.subAreaRepo.findById(aDto.getSubArea());
			if (saById.isPresent()) {
				model.setSubArea(saById.get());
			}
		}

		/**
		 * preenche o dado about
		 */
		model.setAbout(aDto.getAbout());

		return model;
	}
}