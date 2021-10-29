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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Persona Controller")
@Validated
@Slf4j
@RequestMapping(path = "/laburapi")
@RestController
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@ApiOperation(value = "Crea una persona")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 400, message = "BAD REQUEST") })
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/persona")
	public PersonaDto createPersona(@RequestBody @NotNull @Valid PersonaDto persona) {

		log.info("Inicio :: PersonaController.createPersona(PersonaDto): {}", persona);
		PersonaDto result = personaService.createPersona(persona);
		log.info("Fin :: PersonaController.createPersona(PersonaDto): {}", result);

		return result;
	}

	@ApiOperation("Muestra una persona por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/persona/{id}")
	public PersonaDto getPersona(@PathVariable("id") @NotNull @Positive(message = "El ID debe ser positivo") Long id) {

		return personaService.findPersonaById(id);
	}

	@ApiOperation("Actualiza todos los datos de una persona por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/persona/{id}")
	public PersonaDto updatePersona(@PathVariable("id") @NotNull @Positive Long id, @RequestBody @NotNull @Valid PersonaDto persona) {

		log.info("Inicio :: PersonaController.updatePersona(ID): " + id + " (PersonaDto): {}", persona);
		PersonaDto result = personaService.updatePersonaById(id, persona);
		log.info("Fin :: PersonaController.updatePersona(PersonaDto): {}", result);
		
		return result;
	}
	
	@ApiOperation("Elimina una persona por id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "NO CONTENT"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/persona/{id}")
	public void deletePersona(@PathVariable("id") @NotNull @Positive Long id) {

		personaService.deletePersonaById(id);
	}
	
	@ApiOperation("Muestra las personas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
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
