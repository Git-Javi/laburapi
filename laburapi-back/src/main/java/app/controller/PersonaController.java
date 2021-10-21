package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.api.dto.PersonaDto;
import app.service.PersonaService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/laburapi")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@PostMapping(value = "/persona")
	public PersonaDto createPersona(@RequestBody PersonaDto persona) {

		log.info("Inicio :: PersonaController.createPersona(PersonaDto): {}", persona);
		PersonaDto result = personaService.createPersona(persona);
		log.info("Fin :: PersonaController.createPersona(PersonaDto): {}", result);

		return result;
	}

	@GetMapping("/persona/{id}")
	public PersonaDto getPersona(@PathVariable("id") Long id) {

		return personaService.findPersonaById(id);
	}

	@PutMapping("/persona/{id}")
	public PersonaDto updatePersona(@PathVariable("id") Long id, @RequestBody PersonaDto persona) {

		log.info("Inicio :: PersonaController.updatePersona(ID): " + id + " (PersonaDto): {}", persona);
		PersonaDto result = personaService.updatePersonaById(id, persona);
		log.info("Fin :: PersonaController.updatePersona(PersonaDto): {}", result);
		return result;
	}
	
	@DeleteMapping("/persona/{id}")
	public void deletePersona(@PathVariable("id") Long id) {

		personaService.deletePersonaById(id);
	}

}
