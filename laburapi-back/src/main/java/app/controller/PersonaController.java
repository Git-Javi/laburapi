package app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.model.entity.Persona;

@RestController
@RequestMapping(path = "/laburapi")
public class PersonaController {

	@GetMapping(value = "/persona")
	public String createPersona () {

		return new Persona("12345678A","Nombre test","666555666").toString();
	}
	
}
