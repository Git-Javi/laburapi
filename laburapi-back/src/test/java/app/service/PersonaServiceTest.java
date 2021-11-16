package app.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.api.dto.PersonaDto;
import app.exception.NotFoundLaburapiException;
import app.mapper.PersonaMapper;
import app.model.entity.Persona;
import app.repository.PersonaRepository;

@RunWith(value = MockitoJUnitRunner.class)
public class PersonaServiceTest {

	@Mock
	private PersonaRepository personaRepository;

	@Mock
	private PersonaMapper personaMapper;

	@InjectMocks
	private PersonaService personaService = new PersonaServiceImpl();

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void createPersona_shouldReturnPersonaDto() {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "666777888");
		final Persona personaPersist = new Persona("44333222J", "PersonaTest", "666777888");
		Mockito.when(this.personaMapper.personaDtoToPersona(personaRequest)).thenReturn(personaPersist);
		Mockito.when(this.personaRepository.save(personaPersist)).thenReturn(personaPersist);
		Mockito.when(this.personaMapper.personaToPersonaDto(personaPersist)).thenReturn(personaRequest);
		// When
		final PersonaDto personaDtoCreated = this.personaService.createPersona(personaRequest);
		// Then
		assertEquals(personaRequest, personaDtoCreated);
	}

	@Test
	public void findPersonaById_idConocido_shouldReturnPersonaDto() {
		// Given
		final Long id = 1L;
		final boolean existsValue = true;
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "666777888");
		final Optional<Persona> personaPersist = Optional.of(new Persona("44333222J", "PersonaTest", "666777888"));
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		Mockito.when(this.personaRepository.findById(id)).thenReturn(personaPersist);
		Mockito.when(this.personaMapper.personaToPersonaDto(personaPersist.get())).thenReturn(personaRequest);
		// When
		final PersonaDto personaDtoFound = this.personaService.findPersonaById(id);
		// Then
		assertEquals(personaRequest, personaDtoFound);
	}

	@Test(expected = NotFoundLaburapiException.class)
	public void findPersonaById_idDesonocido_shouldReturnException() {
		// Given
		final Long id = 1L;
		final boolean existsValue = false;
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		// When
		this.personaService.findPersonaById(id);
	}

	@Test
	public void updatePersona_idConocido_shouldReturnPersonaDto() {
		// Given
		final Long id = 1L;
		final boolean existsValue = true;
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "666777888");
		final Persona personaPersist = new Persona("44333222J", "PersonaTest", "666777888");
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		Mockito.when(this.personaMapper.mergePersonaIdAndPersonaDtoToPersona(id, personaRequest)).thenReturn(personaPersist);
		Mockito.when(this.personaRepository.save(personaPersist)).thenReturn(personaPersist);
		Mockito.when(this.personaMapper.personaToPersonaDto(personaPersist)).thenReturn(personaRequest);
		// When
		final PersonaDto personaDtoUpdated = this.personaService.updatePersonaById(id, personaRequest);
		// Then
		assertEquals(personaRequest, personaDtoUpdated);
	}

	@Test(expected = NotFoundLaburapiException.class)
	public void updatePersona_idDesonocido_shouldReturnException() {
		// Given
		final Long id = 1L;
		final boolean existsValue = false;
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "666777888");
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		// When
		this.personaService.updatePersonaById(id, personaRequest);
	}

	@Test
	public void deletePersona_idConocido_shouldReturnNothing() {
		// Given
		final Long id = 1L;
		final boolean existsValue = true;
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		doNothing().when(this.personaRepository).deleteById(id);
		// When - Then
		this.personaService.deletePersonaById(id);
	}

	@Test(expected = NotFoundLaburapiException.class)
	public void deletePersona_idDesonocido_shouldReturnException() {
		// Given
		final Long id = 1L;
		final boolean existsValue = false;
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		// When - Then
		this.personaService.deletePersonaById(id);
	}

	@Test
	public void showPersonas_shouldReturnPersonaDtoList() {
		// Given
		final List<Persona> listaPersonas = new ArrayList<>();
		listaPersonas.add(new Persona());
		Mockito.when(this.personaRepository.findAll()).thenReturn(listaPersonas);
		// When
		final List<PersonaDto> listaPersonasDto = this.personaService.findPersonas();
		// Then
		assertEquals(1, listaPersonasDto.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void patchPersona_idConocido_shouldReturnPersonaDto() {
		// Given
		final Long id = 1L;
		final boolean existsValue = true;
		final PersonaDto personaRequest = new PersonaDto(null, "12345678A", "PersonaTest", "999888777");
		final Optional<Persona> personaOptional = Optional.of(new Persona("44333222J", "PersonaTest", "666777888"));
		final Persona personaPersist = personaOptional.get();
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		Mockito.when(this.personaRepository.findById(id)).thenReturn(personaOptional);
		Mockito.when(this.personaMapper.mergePersonaIdAndPersonaDtoToPersona(id, personaRequest)).thenReturn(personaPersist);
		Mockito.when(this.personaRepository.save(personaPersist)).thenReturn(personaPersist);
		Mockito.when(this.personaMapper.personaToPersonaDto(personaPersist)).thenReturn(personaRequest);
		// When
		final PersonaDto personaDtoUpdated = this.personaService.updatePersonaFieldsById(id, fields);
		// Then
		assertEquals(personaRequest, personaDtoUpdated);
	}

	@Test(expected = NotFoundLaburapiException.class)
	public void patchPersona_idDesconocido_shouldReturnPersonaDto() {
		// Given
		final Long id = 1L;
		final boolean existsValue = false;
		Mockito.when(this.personaRepository.existsById(id)).thenReturn(existsValue);
		// When
		this.personaService.updatePersonaFieldsById(id, anyMap());
	}

}
