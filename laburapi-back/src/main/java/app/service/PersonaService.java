package app.service;

import app.api.dto.PersonaDto;

public interface PersonaService {

	public PersonaDto createPersona(PersonaDto persona);

	public PersonaDto findPersonaById(Long id);
	
	public PersonaDto updatePersonaById(Long id, PersonaDto persona);
	
	public PersonaDto personaFind(Long id);
	
	public PersonaDto personaMergeIdSave(Long id, PersonaDto personaDto);
	
}
