package br.com.asoft.usermanagementinterface.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.asoft.usermanagementinterface.application.enums.EnumException;
import br.com.asoft.usermanagementinterface.application.exception.ValidationException;
import br.com.asoft.usermanagementinterface.application.service.UsuarioService;
import br.com.asoft.usermanagementinterface.config.JwtTokenUtil;
import br.com.asoft.usermanagementinterface.model.Usuario;
import br.com.asoft.usermanagementinterface.model.auth.JwtRequest;
import br.com.asoft.usermanagementinterface.model.auth.JwtResponse;
import io.jsonwebtoken.ExpiredJwtException;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsuarioService usuarioService;

	@CacheEvict(value = "usuario_by_username", allEntries = true)
	@PostMapping(value = "/authenticate")
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		Usuario usuario = null;

		Optional<Usuario> optUsuario = usuarioService.findByUsername(authenticationRequest.getUsername());

		if (optUsuario.isPresent()) {
			usuario = optUsuario.get();
		} else {
			return new ResponseEntity<>("Usuario não encontrado!", HttpStatus.UNAUTHORIZED);
		}

		final String token = jwtTokenUtil.generateToken(usuario);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private void authenticate(String username, String password) throws ValidationException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new ValidationException(EnumException.USER_DISABLED);
		} catch (BadCredentialsException e) {
			throw new ValidationException(EnumException.INVALID_CREDENTIALS);
		}
	}

	@PostMapping(value = "/validate-token")
	public ResponseEntity<String> validateToken(@RequestBody Optional<String> token) {

		if (!token.isPresent()) {
			return new ResponseEntity<>("Usuario não possui permissão para acessar o recurso solicitado!",
					HttpStatus.UNAUTHORIZED);
		}

		String username = null;

		try {
			username = jwtTokenUtil.getUsernameFromToken(token.get());
		} catch (IllegalArgumentException e) {
			logger.error("Unable to get JWT Token");
		} catch (ExpiredJwtException e) {
			logger.error("JWT Token has expired");
		}

		if (username != null) {
			Usuario userDetails = this.usuarioService.findByUsername(username).orElse(null);

			if (userDetails != null) {

				if (jwtTokenUtil.validateToken(token.get(), userDetails)) {
					String valueFromClaim = jwtTokenUtil.getValueFromClaim(token.get(), "specific_claim");
					return ResponseEntity.ok(valueFromClaim != null ? valueFromClaim
							: "Usuario possui permissão para acessar o recurso!");
				}
			} else {
				return ResponseEntity.ok("Usuario possui permissão para acessar o recurso!");
			}
		}

		return new ResponseEntity<>("Usuario não possui permissão para acessar o recurso solicitado!",
				HttpStatus.UNAUTHORIZED);
	}
}