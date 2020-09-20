package br.com.asoft.usermanagementinterface.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.model.Usuario;
import br.com.asoft.usermanagementinterface.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@CacheEvict(value = "usuario_by_username", allEntries = true)
	public boolean save(Usuario usuario) {
		repository.save(usuario);
		return true;
	}

	public List<Usuario> getAll() {
		return (List<Usuario>) repository.findAll();
	}

	public Usuario getById(Integer usuarioId) {
		return repository.findById(usuarioId)
				.orElseThrow(() -> new ValidationException(EnumException.CLIENTE_NAO_ENCONTRADO));
	}

	public boolean deleteById(Integer usuarioId) {
		this.repository.deleteById(usuarioId);
		return true;
	}

	public boolean delete(Usuario usuario) {
		this.repository.delete(usuario);
		return true;
	}

	public Optional<Usuario> findByUsername(String username) {
		return this.repository.findByUsername(username);
	}
}