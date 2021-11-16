package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MimeTypeUtils;
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

import app.api.dto.TipoPresenciaDto;
import app.service.TipoPresenciaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Api(tags = "Tipo Presencia Controller")
@Validated
@Slf4j
@RequestMapping(path = "/laburapi", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
@RestController
public class TipoPresenciaController {

	@Autowired
	private TipoPresenciaService tipoPresenciaService;

	@ApiOperation(value = "Crea un Tipo de Presencia")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "CREATED"),
			@ApiResponse(code = 400, message = "BAD REQUEST") })
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(value = "/tipoPresencia")
	public TipoPresenciaDto createTipoPresencia(@RequestBody @NotNull @Valid TipoPresenciaDto tipoPresencia) {

		log.info("Inicio :: TipoPresenciaController.createTipoPresencia(TipoPresenciaDto): {}", tipoPresencia);
		TipoPresenciaDto result = tipoPresenciaService.createTipoPresencia(tipoPresencia);
		log.info("Fin :: TipoPresenciaController.createTipoPresencia(TipoPresenciaDto): {}", result);

		return result;
	}

	@ApiOperation("Muestra un Tipo de Presencia por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping("/tipoPresencia/{id}")
	public TipoPresenciaDto getTipoPresencia(@PathVariable("id") @NotNull @Positive Long id) {

		return tipoPresenciaService.findTipoPresenciaById(id);
	}

	@ApiOperation("Actualiza todos los datos de un Tipo de Presencia por id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.OK)
	@PutMapping("/tipoPresencia/{id}")
	public TipoPresenciaDto updateTipoPresencia(@PathVariable("id") @NotNull @Positive Long id, @RequestBody @NotNull @Valid TipoPresenciaDto tipoPresencia) {

		log.info("Inicio :: TipoPresenciaController.updateTipoPresencia(ID): " + id + " (TipoPresenciaDto): {}", tipoPresencia);
		TipoPresenciaDto result = tipoPresenciaService.updateTipoPresenciaById(id, tipoPresencia);
		log.info("Fin :: TipoPresenciaController.updateTipoPresencia(TipoPresenciaDto): {}", result);
		
		return result;
	}
	
	@ApiOperation("Elimina un Tipo de Presencia por id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "NO CONTENT"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@DeleteMapping("/tipoPresencia/{id}")
	public void deleteTipoPresencia(@PathVariable("id") @NotNull @Positive Long id) {

		tipoPresenciaService.deleteTipoPresenciaById(id);
	}
	
	@ApiOperation("Muestra los Tipos de Presencia")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/tipoPresencias")
	public List<TipoPresenciaDto> showTipoPresencias() {

		List<TipoPresenciaDto> listaTipoPresenciasDto = new ArrayList<>();
		listaTipoPresenciasDto.addAll(tipoPresenciaService.findTipoPresencias());

		for (TipoPresenciaDto p : listaTipoPresenciasDto) {
			log.info("TipoPresencia de la lista: {}", p);
		}
		
		return listaTipoPresenciasDto;
	}
	
	@ApiOperation("Actualiza parcialmente un Tipo de Presencia por id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "BAD REQUEST"),
			@ApiResponse(code = 404, message = "NOT FOUND") })
	@PatchMapping(value = "/tipoPresencia/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public TipoPresenciaDto patchTipoPresencia(@PathVariable("id") @NotNull @Positive Long id, @RequestBody Map<String, Object> fields) {

		return tipoPresenciaService.updateTipoPresenciaFieldsById(id, fields);
	}

}