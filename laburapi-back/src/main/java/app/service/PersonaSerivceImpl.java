package app.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ReflectionUtils;
import app.api.dto.PersonaDto;
import app.exception.FieldNotFoundLaburapiException;
import app.exception.NotFoundLaburapiException;
import app.mapper.PersonaMapper;
import app.model.entity.Persona;
import app.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;

@Validated
@Slf4j
@Service
public class PersonaSerivceImpl implements PersonaService {

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	private PersonaMapper personaMapper;

	@Override
	public PersonaDto createPersona(@NotNull @Valid PersonaDto persona) {

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
	public PersonaDto findPersonaById(@NotNull @Positive Long id) {

		personaExists(id);
		PersonaDto pDto = personaFind(id);
		log.info("Response :: La persona solicitada es: {}", pDto);

		return pDto;
	}

	@Override
	public PersonaDto updatePersonaById(@NotNull @Positive Long id, @NotNull @Valid PersonaDto personaDto) {

		personaExists(id);
		PersonaDto pDto = personaMergeIdSave(id, personaDto);
		log.info("Response :: La persona con id " + id + " se ha actualizado a: {}", pDto);

		return pDto;
	}

	@Override
	public void deletePersonaById(@NotNull @Positive Long id) {

		personaExists(id);
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
	
	@Override
	public PersonaDto updatePersonaFieldsById(@NotNull @Positive Long id, Map<String, Object> fields) {
		personaExists(id);
		// Recuperamos la persona de BBD
		PersonaDto personaDto = personaFind(id);
		// Se mapean los campos sobre la entidaad
		final ObjectMapper mapper = new ObjectMapper();
		final PersonaDto personaNew = mapper.convertValue(fields, PersonaDto.class);
		// Recorremos los campos
		fields.forEach((k, v) -> {
			// Obtenemos el campo
			Field field = ReflectionUtils.findField(PersonaDto.class, k);
			if (field == null) {
				throw new FieldNotFoundLaburapiException("El campo (" + k + ") no existe en la clase");
			}
			// Guadamos en el campo del objeto original el valor del objeto mapeado
			ReflectionUtils.makeAccessible(field); // Es necesario para que permita accerder al campo ya que en nuestra entidad es private
			ReflectionUtils.setField(field, personaDto, ReflectionUtils.getField(field, personaNew));
		});
		PersonaDto result = personaMergeIdSave(id, personaDto);
		return result;
	}

	// -------------------------------------------------------------------------------

	@Override
	public void personaExists(@NotNull @Positive Long id) {

		if (!personaRepository.existsById(id)) {
			throw new NotFoundLaburapiException("No se ha encontrado ese id");
		}
	}

	@Override
	public PersonaDto personaFind(@NotNull @Positive Long id) {

		Persona persona = personaRepository.findById(id).get();
		PersonaDto personaDto = personaMapper.personaToPersonaDto(persona);

		return personaDto;
	}

	@Override
	public PersonaDto personaMergeIdSave(@NotNull @Positive Long id, @NotNull @Valid PersonaDto personaDto) {

		Persona persona = personaMapper.mergePersonaIdAndPersonaDtoToPersona(id, personaDto);
		Persona personaSaved = personaRepository.save(persona);
		PersonaDto personaDtoDevuelta = personaMapper.personaToPersonaDto(personaSaved);

		return personaDtoDevuelta;
	}

}
