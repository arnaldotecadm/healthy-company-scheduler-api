package br.com.asoft.usermanagementinterface.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.asoft.usermanagementinterface.application.service.RegraAgendamentoService;
import br.com.asoft.usermanagementinterface.controller.generics.RestBasicCrud;
import br.com.asoft.usermanagementinterface.model.RegraAgendamento;
import br.com.asoft.usermanagementinterface.model.response.StringResponse;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping(path = "regra_agendamento")
public class RegraAgendamentoController extends RestBasicCrud<RegraAgendamento> {

	@Autowired
	private RegraAgendamentoService service;

	@Autowired
	public RegraAgendamentoController(RegraAgendamentoService service) {
		super(service);
	}

	@GetMapping(path = "get_first", produces = MediaType.APPLICATION_JSON_VALUE)
	public RegraAgendamento getFirst() {
		List<RegraAgendamento> all = service.getAll();
		if (null != all && !all.isEmpty()) {
			return all.get(0);
		} else {
			return new RegraAgendamento();
		}
	}

	@PostMapping(value = "add", produces = "application/json")
	@ResponseBody
	public ResponseEntity<StringResponse> save(@RequestBody RegraAgendamento regraAgendamento) {

		this.service.save(regraAgendamento);

		return new ResponseEntity<>(new StringResponse("Registro Salvo Com Sucesso"), HttpStatus.OK);
	}

}
