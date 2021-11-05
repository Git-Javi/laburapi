package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.api.dto.PresenciaDto;
import app.service.PresenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Presencia Controller")
@Validated
@Slf4j
@RequestMapping(path = "/laburapi")
@RestController
public class PresenciaController {

	@Autowired
	private PresenciaService presenciaService;

	@ApiOperation(value = "Crea una presencia")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 400, message = "BAD REQUEST") })
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/presencia")
	public PresenciaDto createPresencia(@RequestBody @NotNull @Valid PresenciaDto presencia) {

		log.info("Inicio :: PresenciaController.createPresencia(PresenciaDto): {}", presencia);
		PresenciaDto result = presenciaService.createPresencia(presencia);
		log.info("Fin :: PresenciaController.createPresencia(PresenciaDto): {}", result);

		return result;
	}

	@ApiOperation("Muestra una presencia por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/presencia/{id}")
	public PresenciaDto getPresencia(@PathVariable("id") @NotNull @Positive Long id) {

		return presenciaService.findPresenciaById(id);
	}

	@ApiOperation("Actualiza todos los datos de una presencia por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/presencia/{id}")
	public PresenciaDto updatePresencia(@PathVariable("id") @NotNull @Positive Long id, @RequestBody @NotNull @Valid PresenciaDto presencia) {

		log.info("Inicio :: PresenciaController.updatePresencia(ID): " + id + " (PresenciaDto): {}", presencia);
		PresenciaDto result = presenciaService.updatePresenciaById(id, presencia);
		log.info("Fin :: PresenciaController.updatePresencia(PresenciaDto): {}", result);
		
		return result;
	}
	
	@ApiOperation("Elimina una presencia por id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "NO CONTENT"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/presencia/{id}")
	public void deletePresencia(@PathVariable("id") @NotNull @Positive Long id) {

		presenciaService.deletePresenciaById(id);
	}
	
	@ApiOperation("Muestra las presencias")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/presencias")
	public List<PresenciaDto> showPresencias() {

		List<PresenciaDto> listaPresenciasDto = new ArrayList<>();
		listaPresenciasDto.addAll(presenciaService.findPresencias());

		for (PresenciaDto p : listaPresenciasDto) {
			log.info("Presencia de la lista: {}", p);
		}
		
		return listaPresenciasDto;
	}
	
	@ApiOperation("Actualiza parcialmente una presencia por id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@PatchMapping(value = "/presencia/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public PresenciaDto patchPresencia(@PathVariable("id") @NotNull @Positive Long id, @RequestBody Map<String, Object> fields) {

		return presenciaService.updatePresenciaFieldsById(id, fields);
	}

}
