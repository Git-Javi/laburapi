package app.service;

import app.api.dto.PersonaDto;

public interface PersonaService {

	public PersonaDto createPersona(PersonaDto persona);

	public PersonaDto findPersonaById(Long id);
	
	public PersonaDto personaFind(Long id);
	
}
