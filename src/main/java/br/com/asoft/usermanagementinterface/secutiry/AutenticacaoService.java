package br.com.asoft.usermanagementinterface.secutiry;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.asoft.usermanagementinterface.application.service.UsuarioService;
import br.com.asoft.usermanagementinterface.model.Usuario;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioService service;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Usuario> usuario = service.findByUsername(username);

		if (usuario.isPresent()) {
			return usuario.get();
		}

		throw new UsernameNotFoundException("Dados inválidos!");
	}

}
