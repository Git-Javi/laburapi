package app.config.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import app.model.entity.Persona;
import app.repository.PersonaRepository;
import app.utils.AppUtils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	PersonaRepository personaReposotiry;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String passwordId = authentication.getCredentials().toString();

		if (passwordId.isEmpty() || !AppUtils.isNumeric(passwordId)) {
			throw new BadCredentialsException("El valor introducido es incorrecto");
		}

		Optional<Persona> persona = personaReposotiry.findById(Long.valueOf(passwordId));

		if (!persona.isPresent()) {
			throw new BadCredentialsException("No estás registrado en la aplicación");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		Authentication auth = new UsernamePasswordAuthenticationToken("user", passwordId, authorities);
		return auth;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}

}
