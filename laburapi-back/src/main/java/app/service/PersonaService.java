package app.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import app.api.dto.PersonaDto;

public interface PersonaService {

	public PersonaDto createPersona(@NotNull @Valid PersonaDto persona);

	public PersonaDto findPersonaById(@NotNull @Positive Long id);

	public PersonaDto updatePersonaById(@NotNull @Positive Long id, @NotNull @Valid PersonaDto persona);

	public void deletePersonaById(@NotNull @Positive Long id);

	public List<PersonaDto> findPersonas();

	public void personaExists(@NotNull @Positive Long id);

	public PersonaDto personaFind(@NotNull @Positive Long id);

	public PersonaDto personaMergeIdSave(@NotNull @Positive Long id, @NotNull @Valid PersonaDto personaDto);

}
