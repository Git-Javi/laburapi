package app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.Application;
import app.api.dto.PersonaDto;
import app.controller.exception.CustomExceptionHandler;
import app.service.PersonaService;
import app.utils.MockMVCUtils;

@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
public class PersonaControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private MockMVCUtils mMvcU;

	@Autowired
	private PersonaController personaController;

	@MockBean
	private PersonaService personaService;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(personaController)
				.setControllerAdvice(new CustomExceptionHandler())
				.build();
	}

	// ---------------------------------------CREATE(POST)---------------------------------------

	@Test
	public void createPersona_shouldReturnCreated() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "666777888");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(personaRequest);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.CREATED);
		final PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(personaRequest, personaResponse);
	}

	@Test
	public void createPersona_dniNull_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), null, "PersonaTest", "666777888");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_dniVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "", "PersonaTest", "666777888");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_dniFormatoIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "4321B", "PersonaTest", "123456");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_nombreVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "", "666777888");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_nombreNull_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", null, "666777888");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_telefonoVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_telefonoNull_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", null);
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_telefonoFormatoIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(any(), "44333222J", "PersonaTest", "123456");
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void createPersona_headersAceptablesIncorrectos_shouldReturnNotAcceptable() throws Exception {
		// Given
		final PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PersonaTest", "666777888");
		final String invalidAcceptMimeType = MimeTypeUtils.APPLICATION_XML_VALUE;
		Mockito.when(this.personaService.createPersona(personaRequest)).thenReturn(null);
		// When
		final ResultActions result = mockMvc
				.perform(post("/laburapi/persona").accept(invalidAcceptMimeType).content(objectMapper.writeValueAsString(personaRequest)));
		// Then
		result.andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	// ---------------------------------------GET(ById)---------------------------------------

	@Test
	public void getPersona_idCorrecto_shouldReturnOK() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", "PersonaTest", "666777888");
		Mockito.when(this.personaService.findPersonaById(id)).thenReturn(personaRequest);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, get("/laburapi/persona/{id}", id), HttpStatus.OK);
		final PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(personaRequest, personaResponse);
	}

	@Test
	public void getPersona_idIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 0L;
		Mockito.when(this.personaService.findPersonaById(id)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, get("/laburapi/persona/{id}", id), HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void getPersona_headersAceptablesIncorrectos_shouldReturnNotAceptable() throws Exception {
		// Given
		Long id = 1L;
		final String invalidAcceptMimeType = MimeTypeUtils.APPLICATION_XML_VALUE;
		Mockito.when(this.personaService.findPersonaById(id)).thenReturn(null);
		// When
		final ResultActions result = mockMvc.perform(get("/laburapi/persona/{id}", id).accept(invalidAcceptMimeType));
		// Then
		result.andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	// ---------------------------------------UPDATE(PUT)---------------------------------------

	@Test
	public void updatePersona_idCorrecto_shouldReturnOK() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", "PersonaTest", "999888777");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(personaRequest);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.OK);
		final PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(personaRequest, personaResponse);
	}

	@Test
	public void updatePersona_idIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 0L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", "PersonaTest", "999888777");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_dniVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "", "PersonaTest", "999888777");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_dniNull_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, null, "PersonaTest", "999888777");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_dniFormatoIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "12345X", "PersonaTest", "999888777");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_nombreVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", "", "999888777");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_nombreNull_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", null, "999888777");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_telefonoVacio_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", "PersonaTest", "");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_telefonoNull_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", "PersonaTest", null);
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_telefonoFormatoIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(id, "44333222J", "PersonaTest", "123456");
		Mockito.when(this.personaService.updatePersonaById(id, personaRequest)).thenReturn(null);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void updatePersona_headersAceptablesIncorrectos_shouldReturnNotAcceptable() throws Exception {
		// Given
		Long id = 1L;
		final String invalidAcceptMimeType = MimeTypeUtils.APPLICATION_XML_VALUE;
		Mockito.when(this.personaService.findPersonaById(id)).thenReturn(null);
		// When
		final ResultActions result = mockMvc.perform(get("/laburapi/persona/{id}", id).accept(invalidAcceptMimeType));
		// Then
		result.andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	// ---------------------------------------DELETE---------------------------------------

	@Test
	public void deletePersona_idCorrecto_shouldReturnNoContent() throws Exception {
		// Given
		final Long id = 1L;
		doNothing().when(this.personaService).deletePersonaById(id);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, delete("/laburapi/persona/{id}", id), HttpStatus.NO_CONTENT);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void deletePersona_idIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 0L;
		doNothing().when(this.personaService).deletePersonaById(id);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, delete("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@Test
	public void deletePersona_headersAceptablesIncorrectos_shouldReturnNotAcceptable() throws Exception {
		// Given
		Long id = 1L;
		final String invalidAcceptMimeType = MimeTypeUtils.APPLICATION_XML_VALUE;
		doNothing().when(this.personaService).deletePersonaById(id);
		// When
		final ResultActions result = mockMvc.perform(delete("/laburapi/persona/{id}", id).accept(invalidAcceptMimeType));
		// Then
		result.andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	// ---------------------------------------SHOW(GET All)---------------------------------------

	@Test
	public void showPersonas_shouldReturnOk() throws Exception {
		// Given
		Mockito.when(this.personaService.findPersonas()).thenReturn(new ArrayList<PersonaDto>());
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, get("/laburapi/personas"), HttpStatus.OK);
		assertEquals("[]", responseBody);
	}

	@Test
	public void showPersonas_headersAceptablesIncorrectos_shouldReturnNotAceptable() throws Exception {
		// Given
		final String invalidAcceptMimeType = MimeTypeUtils.APPLICATION_XML_VALUE;
		Mockito.when(this.personaService.findPersonas()).thenReturn(null);
		// When
		final ResultActions result = mockMvc.perform(get("/laburapi/personas").accept(invalidAcceptMimeType));
		// Then
		result.andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
	}

	// ---------------------------------------PATCH---------------------------------------

	@SuppressWarnings("unchecked")
	@Test
	public void patchPersona_idCorrecto_shouldReturnOK() throws Exception {
		// Given
		final Long id = 1L;
		final PersonaDto personaRequest = new PersonaDto(null, "12345678A", "PersonaTest", "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		Mockito.when(this.personaService.updatePersonaFieldsById(id, fields)).thenReturn(personaRequest);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id), HttpStatus.OK);
		final PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(personaRequest, personaResponse);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void patchPersona_idIncorrecto_shouldReturnBadRequest() throws Exception {
		// Given
		final Long id = 0L;
		final PersonaDto personaRequest = new PersonaDto(null, "12345678A", "PersonaTest", "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		Mockito.when(this.personaService.updatePersonaFieldsById(id, fields)).thenReturn(personaRequest);
		// When - Then
		final String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, fields, patch("/laburapi/persona/{id}", id),
				HttpStatus.BAD_REQUEST);
		assertTrue(responseBody.isEmpty());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void patchPersona_headersAceptablesIncorrectos_shouldReturnNotAcceptable() throws Exception {
		// Given
		Long id = 1L;
		final String invalidAcceptMimeType = MimeTypeUtils.APPLICATION_XML_VALUE;
		final PersonaDto personaRequest = new PersonaDto(null, "12345678A", "PersonaTest", "999888777");
		final Map<String, Object> fields = objectMapper.convertValue(personaRequest, Map.class);
		Mockito.when(this.personaService.updatePersonaFieldsById(id, fields)).thenReturn(null);
		// When
		final ResultActions result = mockMvc.perform(patch("/laburapi/persona/{id}", id).accept(invalidAcceptMimeType));
		// Then
		result.andExpect(status().is(HttpStatus.NOT_ACCEPTABLE.value()));
	}

}
