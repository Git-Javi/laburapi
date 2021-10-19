package app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/laburapi")
public class PersonaController {

	@GetMapping(value = "/persona")
	public String createPersona () {

		return "PersonaController init Test";
	}
	
}
