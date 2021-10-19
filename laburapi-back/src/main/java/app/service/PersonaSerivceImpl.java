package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.model.entity.Persona;
import app.repository.PersonaRepository;

@Service
public class PersonaSerivceImpl implements PersonaService {
	
	@Autowired
	PersonaRepository personaRepository;

	@Override
	public void createPersona(Persona persona) {
		personaRepository.save(persona);
	}

}
