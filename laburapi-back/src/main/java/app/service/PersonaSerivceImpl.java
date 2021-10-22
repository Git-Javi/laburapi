package app.service;

import java.util.ArrayList;
import java.util.List;

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
	
	@Override
	public PersonaDto updatePersonaById(Long id, PersonaDto personaDto) {

		PersonaDto pDto = personaMergeIdSave(id, personaDto);
		log.info("Response :: La persona con id " + id + " se ha actualizado a: {}", pDto);

		return pDto;
	}
	
	@Override
	public void deletePersonaById(Long id) {

		personaRepository.deleteById(id);
		log.info("Response :: La persona con id " + id + " se ha eliminado");
	}
	
	@Override
	public List<PersonaDto> findPersonas() {

		List<PersonaDto> listaPersonasDto = new ArrayList<>();
		List<Persona> listaPersonas = new ArrayList<>();
		listaPersonas.addAll((List<Persona>) personaRepository.findAll());

		for (Persona p : listaPersonas) {
			PersonaDto pDto = personaMapper.personaToPersonaDto(p);
			listaPersonasDto.add(pDto);
		}

		return listaPersonasDto;
	}
	
	// -------------------------------------------------------------------------------
	
	@Override
	public PersonaDto personaFind(Long id) {

		Persona persona = personaRepository.findById(id).get();
		PersonaDto personaDto = personaMapper.personaToPersonaDto(persona);

		return personaDto;
	}
	
	@Override
	public PersonaDto personaMergeIdSave(Long id, PersonaDto personaDto) {

		Persona persona = personaMapper.mergePersonaIdAndPersonaDtoToPersona(id, personaDto);
		Persona personaSaved = personaRepository.save(persona);
		PersonaDto personaDtoDevuelta = personaMapper.personaToPersonaDto(personaSaved);

		return personaDtoDevuelta;
	}

}
