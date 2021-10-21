package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.api.dto.PersonaDto;
import app.mapper.PersonaMapper;
import app.model.entity.Persona;
import app.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonaSerivceImpl implements PersonaService {
	
	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	private PersonaMapper personaMapper;

	@Override
	public PersonaDto createPersona(PersonaDto persona) {

		log.info("Inicio :: PersonaService.createPersona(PersonaDto): {}", persona);
		Persona personaRequest = personaMapper.personaDtoToPersona(persona);
		log.info("Request :: PersonaService.createPersona(PersonaDto): {}", personaRequest);
		Persona personaResponse = personaRepository.save(personaRequest);
		log.info("Response :: PersonaService.createPersona(PersonaDto): {}", personaResponse);
		PersonaDto result = personaMapper.personaToPersonaDto(personaResponse);
		log.info("Fin :: PersonaService.createPersona(PersonaDto): {}", result);

		return result;
	}
	
	@Override
	public PersonaDto findPersonaById(Long id) {

		PersonaDto pDto = personaFind(id);
		log.info("Response :: La persona solicitada es: {}", pDto);

		return pDto;
	}
	
	// -------------------------------------------------------------------------------
	
	@Override
	public PersonaDto personaFind(Long id) {

		Persona persona = personaRepository.findById(id).get();
		PersonaDto personaDto = personaMapper.personaToPersonaDto(persona);

		return personaDto;
	}

}
