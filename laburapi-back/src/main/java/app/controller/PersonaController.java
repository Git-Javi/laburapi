package app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.api.dto.PersonaDto;
import app.service.PersonaService;
import lombok.extern.slf4j.Slf4j;

@Validated
@Slf4j
@RequestMapping(path = "/laburapi")
@RestController
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/persona")
	public PersonaDto createPersona(@RequestBody @NotNull @Valid PersonaDto persona) {

		log.info("Inicio :: PersonaController.createPersona(PersonaDto): {}", persona);
		PersonaDto result = personaService.createPersona(persona);
		log.info("Fin :: PersonaController.createPersona(PersonaDto): {}", result);

		return result;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/persona/{id}")
	public PersonaDto getPersona(@PathVariable("id") @NotNull @Positive Long id) {

		return personaService.findPersonaById(id);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/persona/{id}")
	public PersonaDto updatePersona(@PathVariable("id") @NotNull @Positive Long id, @RequestBody  @NotNull @Valid PersonaDto persona) {

		log.info("Inicio :: PersonaController.updatePersona(ID): " + id + " (PersonaDto): {}", persona);
		PersonaDto result = personaService.updatePersonaById(id, persona);
		log.info("Fin :: PersonaController.updatePersona(PersonaDto): {}", result);
		
		return result;
	}
	
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/persona/{id}")
	public void deletePersona(@PathVariable("id") @NotNull @Positive Long id) {

		personaService.deletePersonaById(id);
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/personas")
	public List<PersonaDto> showPersonas() {

		List<PersonaDto> listaPersonasDto = new ArrayList<>();
		listaPersonasDto.addAll(personaService.findPersonas());

		for (PersonaDto p : listaPersonasDto) {
			log.info("Persona de la lista: {}", p);
		}
		
		return listaPersonasDto;
	}

}
