package app.service;

import java.util.List;

import app.api.dto.PersonaDto;

public interface PersonaService {

	public PersonaDto createPersona(PersonaDto persona);

	public PersonaDto findPersonaById(Long id);
	
	public PersonaDto updatePersonaById(Long id, PersonaDto persona);
	
	public void deletePersonaById(Long id);
	
	public List<PersonaDto> findPersonas();
	
	public void personaExists(Long id);
	
	public PersonaDto personaFind(Long id);
	
	public PersonaDto personaMergeIdSave(Long id, PersonaDto personaDto);
	
}
