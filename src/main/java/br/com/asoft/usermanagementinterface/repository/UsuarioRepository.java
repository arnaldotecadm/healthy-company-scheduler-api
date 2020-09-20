package br.com.asoft.usermanagementinterface.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.asoft.usermanagementinterface.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	@Query("select u from Usuario u where u.username = :username")
	Optional<Usuario> findByUsername(String username);
}