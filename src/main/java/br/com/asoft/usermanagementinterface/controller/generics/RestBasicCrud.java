package br.com.asoft.usermanagementinterface.controller.generics;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.asoft.usermanagementinterface.application.service.generics.BasicCrudOperation;
import br.com.asoft.usermanagementinterface.model.response.StringResponse;

public class RestBasicCrud<T> {

	private BasicCrudOperation<T> service;

	public RestBasicCrud(BasicCrudOperation<T> service) {
		this.service = service;
	}

	@GetMapping(path = "ping")
	public String ping() {
		return "ok.";
	}

	@GetMapping(path = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<T> getAll() {
		return service.getAll();
	}

	@GetMapping(path = "id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public T getById(@PathVariable("id") Integer id) {
		return service.getById(id);
	}

	@DeleteMapping(path = "remove/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<StringResponse> removeById(@PathVariable("id") Integer id) {
		service.removeById(id);
		return new ResponseEntity<>(new StringResponse("registro removido com sucesso"), HttpStatus.OK);
	}

}
