package br.com.asoft.usermanagementinterface.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.asoft.usermanagementinterface.application.comparators.UsuarioComparator;
import br.com.asoft.usermanagementinterface.application.service.UsuarioService;
import br.com.asoft.usermanagementinterface.model.Usuario;
import br.com.asoft.usermanagementinterface.model.response.StringResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping(value = "all")
	public List<Usuario> getAll() {
		List<Usuario> usuarios = this.service.getAll();

		Collections.sort(usuarios, new UsuarioComparator());

		return usuarios;
	}

	@GetMapping(value = "id/{usuarioId}")
	public Usuario getById(@PathVariable("usuarioId") Integer usuarioId) {
		return this.service.getById(usuarioId);
	}

	@PostMapping(value = "add", produces = "application/json")
	@ResponseBody
	public ResponseEntity<StringResponse> save(@RequestBody Usuario usuario) {

		if (usuario.getId() == null) {
			usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
		} else {
			Usuario user = this.service.getById(usuario.getId());

			if (user != null && (usuario.getPerfis() != null || !usuario.getPerfis().isEmpty())) {
				usuario.setPerfis(user.getPerfis());
			}

			if (user != null && !user.getPassword().equals(usuario.getPassword())) {
				usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
			}
		}

		this.service.save(usuario);

		return new ResponseEntity<>(new StringResponse("Registro Salvo Com Sucesso"), HttpStatus.OK);
	}

	@DeleteMapping(value = "remove/{usuarioId}")
	public String delete(@PathVariable("usuarioId") Integer usuarioId) {
		this.service.deleteById(usuarioId);
		return "{ \"message\" : \"Registro removido com Sucesso\"}";
	}

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class IdentificacaoAddSoftware {
	private Integer usuarioId;
	private Integer softwareId;
}
