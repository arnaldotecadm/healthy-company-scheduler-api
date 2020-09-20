package br.com.asoft.usermanagementinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.asoft.usermanagementinterface.application.service.LocalService;
import br.com.asoft.usermanagementinterface.controller.generics.RestBasicCrud;
import br.com.asoft.usermanagementinterface.model.Local;
import br.com.asoft.usermanagementinterface.model.response.StringResponse;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping(path = "local")
public class LocalController extends RestBasicCrud<Local> {

	@Autowired
	private LocalService service;

	@Autowired
	public LocalController(LocalService service) {
		super(service);
	}

	@PostMapping(value = "add", produces = "application/json")
	@ResponseBody
	public ResponseEntity<StringResponse> save(@RequestBody Local local) {

		this.service.save(local);

		return new ResponseEntity<>(new StringResponse("Registro Salvo Com Sucesso"), HttpStatus.OK);
	}

}
