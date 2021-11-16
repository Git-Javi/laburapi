package app.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ReflectionUtils;
import app.api.dto.TipoPresenciaDto;
import app.exception.FieldNotFoundLaburapiException;
import app.exception.NotFoundLaburapiException;
import app.mapper.TipoPresenciaMapper;
import app.model.entity.TipoPresencia;
import app.repository.TipoPresenciaRepository;
import lombok.extern.slf4j.Slf4j;

@Validated
@Slf4j
@Service
public class TipoPresenciaServiceImpl implements TipoPresenciaService {

	@Autowired
	private TipoPresenciaRepository tipoPresenciaRepository;

	@Autowired
	private TipoPresenciaMapper tipoPresenciaMapper;

	@Override
	public TipoPresenciaDto createTipoPresencia(@NotNull @Valid TipoPresenciaDto tipoPresencia) {

		log.info("Inicio :: TipoPresenciaService.createTipoPresencia(TipoPresenciaDto): {}", tipoPresencia);
		TipoPresencia tipoPresenciaRequest = tipoPresenciaMapper.tipoPresenciaDtoToTipoPresencia(tipoPresencia);
		log.info("Request :: TipoPresenciaService.createTipoPresencia(TipoPresenciaDto): {}", tipoPresenciaRequest);
		TipoPresencia tipoPresenciaResponse = tipoPresenciaRepository.save(tipoPresenciaRequest);
		log.info("Response :: TipoPresenciaService.createTipoPresencia(TipoPresenciaDto): {}", tipoPresenciaResponse);
		TipoPresenciaDto result = tipoPresenciaMapper.tipoPresenciaToTipoPresenciaDto(tipoPresenciaResponse);
		log.info("Fin :: TipoPresenciaService.createTipoPresencia(TipoPresenciaDto): {}", result);

		return result;
	}

	@Override
	public TipoPresenciaDto findTipoPresenciaById(@NotNull @Positive Long id) {

		tipoPresenciaExists(id);
		TipoPresenciaDto pDto = tipoPresenciaFind(id);
		log.info("Response :: El Tipo de Presencia solicitada es: {}", pDto);

		return pDto;
	}

	@Override
	public TipoPresenciaDto updateTipoPresenciaById(@NotNull @Positive Long id, @NotNull @Valid TipoPresenciaDto tipoPresenciaDto) {

		tipoPresenciaExists(id);
		TipoPresenciaDto pDto = tipoPresenciaMergeIdSave(id, tipoPresenciaDto);
		log.info("Response :: El Tipo de Presencia con id " + id + " se ha actualizado a: {}", pDto);

		return pDto;
	}

	@Override
	public void deleteTipoPresenciaById(@NotNull @Positive Long id) {

		tipoPresenciaExists(id);
		tipoPresenciaRepository.deleteById(id);
		log.info("Response :: El Tipo de Presencia con id " + id + " se ha eliminado");
	}

	@Override
	public List<TipoPresenciaDto> findTipoPresencias() {

		List<TipoPresenciaDto> listaTipoPresenciasDto = new ArrayList<>();
		List<TipoPresencia> listaTipoPresencias = new ArrayList<>();
		listaTipoPresencias.addAll((List<TipoPresencia>) tipoPresenciaRepository.findAll());

		for (TipoPresencia p : listaTipoPresencias) {
			TipoPresenciaDto pDto = tipoPresenciaMapper.tipoPresenciaToTipoPresenciaDto(p);
			listaTipoPresenciasDto.add(pDto);
		}

		return listaTipoPresenciasDto;
	}
	
	@Override
	public TipoPresenciaDto updateTipoPresenciaFieldsById(@NotNull @Positive Long id, Map<String, Object> fields) {
		tipoPresenciaExists(id);
		// Recuperamos la tipoPresencia de BBD
		TipoPresenciaDto tipoPresenciaDto = tipoPresenciaFind(id);
		// Se mapean los campos sobre la entidaad
		final ObjectMapper mapper = new ObjectMapper();
		final TipoPresenciaDto tipoPresenciaNew = mapper.convertValue(fields, TipoPresenciaDto.class);
		// Recorremos los campos
		fields.forEach((k, v) -> {
			// Obtenemos el campo
			Field field = ReflectionUtils.findField(TipoPresenciaDto.class, k);
			if (field == null) {
				throw new FieldNotFoundLaburapiException("El campo (" + k + ") no existe en la clase");
			}
			// Guadamos en el campo del objeto original el valor del objeto mapeado
			ReflectionUtils.makeAccessible(field); // Es necesario para que permita accerder al campo ya que en nuestra entidad es private
			ReflectionUtils.setField(field, tipoPresenciaDto, ReflectionUtils.getField(field, tipoPresenciaNew));
		});
		TipoPresenciaDto result = tipoPresenciaMergeIdSave(id, tipoPresenciaDto);
		return result;
	}

	// -------------------------------------------------------------------------------

	@Override
	public void tipoPresenciaExists(@NotNull @Positive Long id) {

		if (!tipoPresenciaRepository.existsById(id)) {
			throw new NotFoundLaburapiException("No se ha encontrado ese id");
		}
	}

	@Override
	public TipoPresenciaDto tipoPresenciaFind(@NotNull @Positive Long id) {

		TipoPresencia tipoPresencia = tipoPresenciaRepository.findById(id).get();
		TipoPresenciaDto tipoPresenciaDto = tipoPresenciaMapper.tipoPresenciaToTipoPresenciaDto(tipoPresencia);

		return tipoPresenciaDto;
	}

	@Override
	public TipoPresenciaDto tipoPresenciaMergeIdSave(@NotNull @Positive Long id, @NotNull @Valid TipoPresenciaDto tipoPresenciaDto) {

		TipoPresencia tipoPresencia = tipoPresenciaMapper.mergeTipoPresenciaIdAndTipoPresenciaDtoToTipoPresencia(id, tipoPresenciaDto);
		TipoPresencia tipoPresenciaSaved = tipoPresenciaRepository.save(tipoPresencia);
		TipoPresenciaDto tipoPresenciaDtoDevuelta = tipoPresenciaMapper.tipoPresenciaToTipoPresenciaDto(tipoPresenciaSaved);

		return tipoPresenciaDtoDevuelta;
	}

}

