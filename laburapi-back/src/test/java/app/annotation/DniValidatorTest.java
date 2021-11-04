package app.annotation;

import static org.junit.Assert.assertEquals;
import javax.validation.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import app.Application;
import app.api.dto.PersonaDto;
import app.service.PersonaService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
@EnableAutoConfiguration
public class DniValidatorTest {

	@Autowired
	PersonaService personaService;
	
	@Autowired
	AnnotationServiceImpl annotationServiceImpl;
	
	PersonaDto personaTest = new PersonaDto();

	@Test (expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteMenosNueveCaracteresTest() {

		String dni = "1234567A";
		annotationServiceImpl.dnitester(dni);
	}

	@Test (expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteMasNueveCaracteresTest() {

		String dni = "123456789A";
		annotationServiceImpl.dnitester(dni);
	}
	
	@Test (expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteNullTest() {

		String dni = null;
		annotationServiceImpl.dnitester(dni);
	}

	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteValoresNoNumericosTest() {

		String dni = "abcde678A";
		annotationServiceImpl.dnitester(dni);
	}

	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteEmpezarPorLetraTest() {

		String dni = "A1234567A";
		annotationServiceImpl.dnitester(dni);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteNoFinalizarConLetraTest() {

		String dni = "123456789";
		annotationServiceImpl.dnitester(dni);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteFinalizarConLetraMinusculaTest() {

		String dni = "12345678a";
		annotationServiceImpl.dnitester(dni);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteFinalizarConLetraITest() {

		String dni = "12345678I";
		annotationServiceImpl.dnitester(dni);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteFinalizarConLetraÑTest() {

		String dni = "12345678Ñ";
		annotationServiceImpl.dnitester(dni);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteFinalizarConLetraOTest() {

		String dni = "12345678O";
		annotationServiceImpl.dnitester(dni);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void constraintDniNoPermiteFinalizarConLetraUTest() {

		String dni = "12345678U";
		annotationServiceImpl.dnitester(dni);
	}

	@Test
	public void constraintDniEjemploCorrectoTest() {

		personaTest.setId(1l);
		personaTest.setDni("44333777E");
		personaTest.setNombre("Manolo");
		personaTest.setTelefono("666333222");

		personaService.createPersona(personaTest);
		assertEquals("El DNI es válido y la persona lo devolverá con el get", "44333777E", personaTest.getDni());
	}
	
}
