package app.mapper;


	import static org.junit.Assert.*;

	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.test.context.junit4.SpringRunner;

	import app.Application;
	import app.api.dto.PersonaDto;
	import app.model.entity.Persona;


	@RunWith(SpringRunner.class)
	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { Application.class })
	@EnableAutoConfiguration // @SpringBootTest -> Indica al test que cargue todo el Contexto de Spring (beans) cada vez que se ejecute la prueba.
	public class PersonaMapperTest {
		
		@Autowired
		PersonaMapper personaMapper;

		@Test
		public void conversionCamposPersonaToPersonaDtoTest() {

			Persona persona = new Persona("44333222J","PepeTest", "981223344");
			
			PersonaDto pDto = personaMapper.personaToPersonaDto(persona);
			
			assertNull("El id viene vacio", pDto.getId());
			assertNotNull("El DNI apunta a null", pDto.getDni());
			assertNotNull("El nombre apunta a null", pDto.getNombre());
			assertNotNull("El TLF apunta a null", pDto.getTelefono());
		}
		
		@Test
		public void conversionCamposPersonaDtoToPersonaTest() {

			PersonaDto personaDto = new PersonaDto(1L,"44333222J","ManoloTest", "987456123");
			
			Persona persona = personaMapper.personaDtoToPersona(personaDto);
			
			assertNotNull("El id no viene vacio", persona.getId());
			assertNotNull("El DNI apunta a null", persona.getDni());
			assertNotNull("El nombre apunta a null", persona.getNombre());
			assertNotNull("El TLF apunta a null", persona.getTelefono());
		}

		
		@Test
		public void mapperIgnoraCampoIdEnConversionTest() {
			
			Persona persona = new Persona();
			PersonaDto personaDto = new PersonaDto(3L,"44333222J","PepeTest", "999888777");
			
			persona = personaMapper.mergePersonaIdAndPersonaDtoToPersona(1L,personaDto);
			
			assertEquals("El id ha de ser 1", Long.valueOf(1), persona.getId());
			assertEquals("El DNI debe ser el mismo", "44333222J", persona.getDni());
			assertEquals("El nombre debe ser el mismo", "PepeTest", persona.getNombre());
			assertEquals("El telefono debe ser el mismo", "999888777", persona.getTelefono());
		}
		
}
