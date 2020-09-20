package br.com.asoft.usermanagementinterface.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.application.service.AgendamentoService;
import br.com.asoft.usermanagementinterface.application.service.RegraAgendamentoService;
import br.com.asoft.usermanagementinterface.application.service.UsuarioService;
import br.com.asoft.usermanagementinterface.controller.generics.RestBasicCrud;
import br.com.asoft.usermanagementinterface.model.Agendamento;
import br.com.asoft.usermanagementinterface.model.RegraAgendamento;
import br.com.asoft.usermanagementinterface.model.Usuario;
import br.com.asoft.usermanagementinterface.model.dto.AgendamentoDTO;
import br.com.asoft.usermanagementinterface.model.response.StringResponse;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping(path = "agendamento")
public class AgendamentoController extends RestBasicCrud<Agendamento> {

	@Autowired
	private AgendamentoService service;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private RegraAgendamentoService regraAgendamentoService;

	@Autowired
	public AgendamentoController(AgendamentoService service) {
		super(service);
	}

	@GetMapping(path = "identificacao_agendamento/{idUsuario}/{dataAgendamento}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AgendamentoDTO getById(@PathVariable("idUsuario") Integer idUsuario,
			@PathVariable("dataAgendamento") String dataAgendamento) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(dataAgendamento));

		Usuario usuario = this.usuarioService.getById(idUsuario);

		Agendamento agd = service.getByUsuarioAndDataAgendamento(usuario, c);

		return new AgendamentoDTO(agd);
	}

	@GetMapping(path = "allDTO", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AgendamentoDTO> getAllDTO() {
		List<Agendamento> all = service.getAll();
		List<AgendamentoDTO> listDTO = new ArrayList<>();

		if (null != all) {
			for (Agendamento a : all) {
				listDTO.add(new AgendamentoDTO(a));
			}
		}

		return listDTO;
	}

	@PostMapping(value = "add", produces = "application/json")
	@ResponseBody
	public ResponseEntity<StringResponse> save(@RequestBody AgendamentoDTO agendamentoDTO) {
		RegraAgendamento regraAgendamento = null;
		List<RegraAgendamento> all = this.regraAgendamentoService.getAll();
		if (null != all && !all.isEmpty()) {
			regraAgendamento= all.get(0);
		} else {
			regraAgendamento = new RegraAgendamento();
		}

		/**
		 * Verifica se há regra para limite máximo de pessoas por dia
		 */
		if(null != regraAgendamento.getLimitePessoasPorDia() && regraAgendamento.getLimitePessoasPorDia() > 0) {
			List<Agendamento> byDataAgendamento = this.service.getByDataAgendamento(agendamentoDTO.getDataAgendamento());
			
			if(byDataAgendamento.size() >= regraAgendamento.getLimitePessoasPorDia()) {
				throw new ValidationException(EnumException.FALHA_VALIDACAO_LIMITE_POR_DIA);
			}
		}
		
		Agendamento agendamento = service.convertDTOToModel(agendamentoDTO);
		
		this.service.save(agendamento);

		return new ResponseEntity<>(new StringResponse("Registro Salvo Com Sucesso"), HttpStatus.OK);
	}

}
