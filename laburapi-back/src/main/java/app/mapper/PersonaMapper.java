package app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import app.api.dto.PersonaDto;
import app.model.entity.Persona;

@Mapper
public interface PersonaMapper {

	PersonaDto personaToPersonaDto (Persona persona);
	
	Persona personaDtoToPersona (PersonaDto personaDto);
	
	@Mapping(source = "personaId", target = "id")
	Persona mergePersonaIdAndPersonaDtoToPersona (Long personaId, PersonaDto personaDTo);
	
}
