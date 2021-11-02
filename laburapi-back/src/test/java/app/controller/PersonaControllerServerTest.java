package app.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

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
import app.utils.MockMVCUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
public class PersonaControllerServerTest {

	private MockMvc mockMvc;

	@Autowired
	private MockMVCUtils mMvcU;

	@Autowired
	private PersonaController personaController;

	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(personaController).build();
	}

	// ---------------------------------------GET(All)---------------------------------------

	@Test
	public void metodo01GetDebeResponderOkYJsonVacioTest() throws Exception {

		String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, get("/laburapi/personas"), HttpStatus.OK);
		assertEquals("[]", responseBody);
	}

	// ---------------------------------------POST---------------------------------------

	@Test
	public void metodo02PostConIdNullDebeResponderCreatedConIdAutogeneradoTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(null, "44333222J", "PostTest", "666777888");
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.CREATED);

		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "44333222J", "PostTest", "666777888"), personaResponse);
	}

	@Test
	public void metodo03PostConDniNullDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, null, "PostTest", "666777888");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo04PostConDniVacioDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "", "PostTest", "");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo05PostConFormatoDniIncorrectoDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "4321B", "PostTest", "123456");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo06PostConNombreVacioDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "", "666777888");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo07PostConNombreNullDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", null, "666777888");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo08PostConTelefonoVacioDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PostTest", "");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo09PostConTelefonoNullDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PostTest", null);
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo10PostConFormatoTelefonoIncorrectoDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PostTest", "123456");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, post("/laburapi/persona"), HttpStatus.BAD_REQUEST);
	}

	// ---------------------------------------GET(ById)---------------------------------------

	@Test
	public void metodo11GetConIdDebeResponderOKTest() throws Exception {

		String responseBody = mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, get("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "44333222J", "PostTest", "666777888"), personaResponse);
	}

	@Test
	public void metodo12GetConIdInexistenteDebeResponderNotFoundTest() throws Exception {

		mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, get("/laburapi/persona/5"), HttpStatus.NOT_FOUND);
	}

	// ---------------------------------------PUT---------------------------------------

	@Test
	public void metodo13PutConIdCorrectoYPayloadCorrectoDebeResponderOKTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PutTest", "999888777");
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(personaRequest, personaResponse);
	}

	@Test
	public void metodo14PutConIdInexistenteDebeResponderNotFoundTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PutTest", "999888777");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/5"), HttpStatus.NOT_FOUND);
	}

	@Test
	public void metodo15PutNoTieneEnCuentaIdPayloadDebeResponderOKTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(3L, "44333222J", "PutTest", "999888777");
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "44333222J", "PutTest", "999888777"), personaResponse);
	}

	@Test
	public void metodo16PutConDniVacioDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "", "PutTest", "999888777");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo17PutConDniNullDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, null, "PutTest", "999888777");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo18PutConFormatoDniIncorrectoDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "12345J", "PutTest", "999888777");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo19PutConNombreVacioDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "", "999888777");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo20PutConNombreNullDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", null, "999888777");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo21PutConTelefonoVacioDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PutTest", "");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo22PutConTelefonoNullDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PutTest", null);
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void metodo23PutConFormatoTelefonoIncorrectoDebeResponderBadRequestTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PutTest", "123456");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, put("/laburapi/persona/1"), HttpStatus.BAD_REQUEST);
	}

	// ---------------------------------------PATCH---------------------------------------

	@Test
	public void metodo24PatchConIdCorrectoYPayloadCorrectoDebeResponderOKTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PatchTest", "999888777");
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, patch("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "44333222J", "PatchTest", "999888777"), personaResponse);
	}

	@Test
	public void metodo25PatchNoTieneEnCuentaIdPayloadDebeResponderOKTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(3L, "44333222J", "PatchTest", "777777777");
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, patch("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "44333222J", "PatchTest", "777777777"), personaResponse);
	}

	@Test
	public void metodo26PatchConIdInexistenteDebeResponderNotFoundTest() throws Exception {

		PersonaDto personaRequest = new PersonaDto(1L, "44333222J", "PatchTest", "999888777");
		mMvcU.controllerResponseTesterPayloadUtil(mockMvc, personaRequest, patch("/laburapi/persona/5"), HttpStatus.NOT_FOUND);
	}

	@Test
	public void metodo27PatchActualizaSoloDniDebeResponderOKTest() throws Exception {

		String jsonString = "{\"dni\":\"33222777E\"}";
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, jsonString, patch("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "33222777E", "PatchTest", "777777777"), personaResponse);
	}

	@Test
	public void metodo28PatchActualizaSoloNombreDebeResponderOKTest() throws Exception {

		String jsonString = "{\"nombre\":\"SoloNombrePatch\"}";
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, jsonString, patch("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "33222777E", "SoloNombrePatch", "777777777"), personaResponse);
	}

	@Test
	public void metodo29PatchActualizaSoloTelefonoDebeResponderOKTest() throws Exception {

		String jsonString = "{\"telefono\":\"666777888\"}";
		String responseBody = mMvcU.controllerResponseTesterPayloadUtil(mockMvc, jsonString, patch("/laburapi/persona/1"), HttpStatus.OK);
		PersonaDto personaResponse = objectMapper.readValue(responseBody, PersonaDto.class);
		assertEquals(new PersonaDto(1L, "33222777E", "SoloNombrePatch", "666777888"), personaResponse);
	}

	// ---------------------------------------DELETE---------------------------------------

	@Test
	public void metodo30DeleteDebeResponderNoContentTest() throws Exception {

		mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, delete("/laburapi/persona/1"), HttpStatus.NO_CONTENT);
	}

	@Test
	public void metodo31DeleteConIdInexistenteDebeResponderNotFoundTest() throws Exception {

		mMvcU.controllerResponseTesterNoPayloadUtil(mockMvc, delete("/laburapi/persona/1"), HttpStatus.NOT_FOUND);
	}

}
