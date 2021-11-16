package app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Map;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.Application;
import app.api.dto.PersonaDto;
import app.controller.exception.CustomExceptionHandler;
import app.utils.MockMVCUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
public class PersonaControllerIT {

	private MockMvc mockMvc;

	@Autowired
	private MockMVCUtils mMvcU;

	@Autowired
	private PersonaController personaController;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(personaController).setControllerAdvice(new CustomExceptionHandler()).build();
	}

	@Test
	public void test01_createPersona_idPayloadEsIgnorado_shouldReturnOk() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "666777888");
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.CREATED);
		// When - Then
		final PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "44333222J", "PersonaTest", "666777888"), personaResponse);
	}

	@Test
	public void test02_updatePersona_idInexistente_shouldReturnNotFound() throws Exception {
		//// Given
		final Long id = 33L;
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PutTest", "999888777");
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.NOT_FOUND);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void test03_updatePersona_idPayloadEsIgnorado_shouldReturnOK() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "NewPersonaTest", "666777666");
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.OK);
		final PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(id, "44333222J", "NewPersonaTest", "666777666"), personaResponse);
	}

	@Test
	public void test04_patchPersona_idPayloadEsIgnorado_shouldReturnOK() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "98765432J", "NewPersonaTest", "777777777");
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, patch("/laburapi/persona/{id}", id),
				HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(id, "98765432J", "NewPersonaTest", "777777777"), personaResponse);
	}

	@Test
	public void test05_patchPersona_idInexistente_shouldReturnNotFound() throws Exception {
		// Given
		final Long id = 33L;
		final PersonaDto personaRequest = new PersonaDto(any(), "98765432J", "NewPersonaTest", "777777777");
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, patch("/laburapi/persona/{id}", id),
				HttpStatus.NOT_FOUND);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test06_patchPersona_dniVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "", "PersonaTest", "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test07_patchPersona_dniNull_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), null, "PersonaTest", "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test08_patchPersona_dniFormatoIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "123A", "PersonaTest", "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test09_patchPersona_nombreVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "98765432J", "", "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test10_patchPersona_nombreNull_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "98765432J", null, "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test11_patchPersona_telefonoVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "98765432J", "PersonaTest", "");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test12_patchPersona_telefonoNull_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "98765432J", "PersonaTest", null);
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test13_patchPersona_telefonoFormatoIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(any(), "98765432J", "PersonaTest", "12345");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void test14_patchPersona_actualizarDni_shouldReturnrOK() throws Exception {
		// Given
		final Long id = 1L;
		final String jsonString = "{\"dni\":\"33222777E\"}";
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, jsonString, patch("/laburapi/persona/{id}", id),
				HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(id, "33222777E", "NewPersonaTest", "777777777"), personaResponse);
	}

	@Test
	public void test15_patchPersona_actualizarNombre_shouldReturnOK() throws Exception {
		// Given
		final Long id = 1L;
		final String jsonString = "{\"nombre\":\"SoloNombrePatch\"}";
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, jsonString, patch("/laburapi/persona/{id}", id),
				HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(id, "33222777E", "SoloNombrePatch", "777777777"), personaResponse);
	}

	@Test
	public void test16_patchPersona_actualizarTelefono_shouldReturnOK() throws Exception {
		// Given
		final Long id = 1L;
		final String jsonString = "{\"telefono\":\"666777888\"}";
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, jsonString, patch("/laburapi/persona/{id}", id),
				HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(id, "33222777E", "SoloNombrePatch", "666777888"), personaResponse);
	}

	@Test
	public void test17_patchPersona_campoDesconocido_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final String jsonString = "{\"campoDesconocido\":\"abc\"}";
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, jsonString, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void getPersona_idInexistente_shouldReturnNotFound() throws Exception {
		// Given
		final Long id = 33L;
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, get("/laburapi/persona/{id}", id), HttpStatus.NOT_FOUND);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void deletePersona_idInexistente_shouldReturnNotFound() throws Exception {
		// Given
		final Long id = 33L;
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, delete("/laburapi/persona/{id}", id), HttpStatus.NOT_FOUND);
		assertTrue(responseBody.isEmpty());
	}

}
