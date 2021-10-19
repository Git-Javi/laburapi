package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.entity.Persona;
import app.service.PersonaService;

@RestController
@RequestMapping(path = "/laburapi")
public class PersonaController {

	@Autowired
	private PersonaService personaService;
	
	@GetMapping(value = "/persona")
	public void createPersona() {

		personaService.createPersona(new Persona("12345678A","Nombre test","666555666"));
	}
	
}
