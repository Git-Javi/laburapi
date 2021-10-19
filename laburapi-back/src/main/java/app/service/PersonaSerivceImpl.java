package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.api.dto.PersonaDto;
import app.mapper.PersonaMapper;
import app.model.entity.Persona;
import app.repository.PersonaRepository;

@Service
public class PersonaSerivceImpl implements PersonaService {
	
	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	private PersonaMapper personaMapper;

	@Override
	public PersonaDto createPersona(PersonaDto persona) {

		Persona personaRequest = personaMapper.personaDtoToPersona(persona);

		Persona personaResponse = personaRepository.save(personaRequest);

		PersonaDto result = personaMapper.personaToPersonaDto(personaResponse);

		return result;
	}

}
