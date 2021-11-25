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
import app.api.dto.PresenciaDto;
import app.exception.FieldNotFoundLaburapiException;
import app.exception.NotFoundLaburapiException;
import app.mapper.PresenciaMapper;
import app.model.entity.Presencia;
import app.repository.PresenciaRepository;
import lombok.extern.slf4j.Slf4j;

@Validated
@Slf4j
@Service
public class PresenciaServiceImpl implements PresenciaService {

	@Autowired
	PresenciaRepository presenciaRepository;

	@Autowired
	private PresenciaMapper presenciaMapper;
	
	@Autowired
	private PersonaService personaServcie;

	@Override
	public PresenciaDto createPresencia(@NotNull @Valid PresenciaDto presencia) {

		log.info("Inicio :: PresenciaService.createPresencia(PresenciaDto): {}", presencia);
		Presencia presenciaRequest = presenciaMapper.presenciaDtoToPresencia(presencia);
		log.info("Request :: PresenciaService.createPresencia(PresenciaDto): {}", presenciaRequest);
		Presencia presenciaResponse = presenciaRepository.save(presenciaRequest);
		log.info("Response :: PresenciaService.createPresencia(PresenciaDto): {}", presenciaResponse);
		PresenciaDto result = presenciaMapper.presenciaToPresenciaDto(presenciaResponse);
		log.info("Fin :: PresenciaService.createPresencia(PresenciaDto): {}", result);

		return result;
	}

	@Override
	public PresenciaDto findPresenciaById(@NotNull @Positive Long id) {

		presenciaExists(id);
		PresenciaDto pDto = presenciaFind(id);
		log.info("Response :: La presencia solicitada es: {}", pDto);

		return pDto;
	}

	@Override
	public PresenciaDto updatePresenciaById(@NotNull @Positive Long id, @NotNull @Valid PresenciaDto presenciaDto) {

		presenciaExists(id);
		PresenciaDto pDto = presenciaMergeIdSave(id, presenciaDto);
		log.info("Response :: La presencia con id " + id + " se ha actualizado a: {}", pDto);

		return pDto;
	}

	@Override
	public void deletePresenciaById(@NotNull @Positive Long id) {

		presenciaExists(id);
		presenciaRepository.deleteById(id);
		log.info("Response :: La presencia con id " + id + " se ha eliminado");
	}

	@Override
	public List<PresenciaDto> findPresencias() {

		List<PresenciaDto> listaPresenciasDto = new ArrayList<>();
		List<Presencia> listaPresencias = new ArrayList<>();
		listaPresencias.addAll((List<Presencia>) presenciaRepository.findAll());

		for (Presencia p : listaPresencias) {
			PresenciaDto pDto = presenciaMapper.presenciaToPresenciaDto(p);
			listaPresenciasDto.add(pDto);
		}

		return listaPresenciasDto;
	}
	
	@Override
	public PresenciaDto updatePresenciaFieldsById(@NotNull @Positive Long id, Map<String, Object> fields) {
		presenciaExists(id);
		// Recuperamos la presencia de BBD
		PresenciaDto presenciaDto = presenciaFind(id);
		// Se mapean los campos sobre la entidad
		final ObjectMapper mapper = new ObjectMapper();
		final PresenciaDto presenciaNew = mapper.convertValue(fields, PresenciaDto.class);
		// Recorremos los campos
		fields.forEach((k, v) -> {
			// Obtenemos el campo
			Field field = ReflectionUtils.findField(PresenciaDto.class, k);
			if (field == null) {
				throw new FieldNotFoundLaburapiException("El campo (" + k + ") no existe en la clase");
			}
			// Guardamos en el campo del objeto original el valor del objeto mapeado
			ReflectionUtils.makeAccessible(field); // Es necesario para que permita accerder al campo ya que en nuestra entidad es private
			ReflectionUtils.setField(field, presenciaDto, ReflectionUtils.getField(field, presenciaNew));
		});
		PresenciaDto result = presenciaMergeIdSave(id, presenciaDto);
		return result;
	}
	
	@Override
	public List<PresenciaDto> findPresenciasByPersonaId(@NotNull @Positive Long personaId) {

		personaServcie.personaExists(personaId);
		List<PresenciaDto> listaPresenciasDto = new ArrayList<>();
		List<Presencia> listaPresencias = new ArrayList<>();
		listaPresencias.addAll((List<Presencia>) presenciaRepository.findAllPresenciaByPersonaId(personaId));

		for (Presencia p : listaPresencias) {
			PresenciaDto pDto = presenciaMapper.presenciaToPresenciaDto(p);
			listaPresenciasDto.add(pDto);
		}

		return listaPresenciasDto;
	}

	// -------------------------------------------------------------------------------

	@Override
	public void presenciaExists(@NotNull @Positive Long id) {

		if (!presenciaRepository.existsById(id)) {
			throw new NotFoundLaburapiException("No se ha encontrado ese id");
		}
	}

	@Override
	public PresenciaDto presenciaFind(@NotNull @Positive Long id) {

		Presencia presencia = presenciaRepository.findById(id).get();
		PresenciaDto presenciaDto = presenciaMapper.presenciaToPresenciaDto(presencia);

		return presenciaDto;
	}

	@Override
	public PresenciaDto presenciaMergeIdSave(@NotNull @Positive Long id, @NotNull @Valid PresenciaDto presenciaDto) {

		Presencia presencia = presenciaMapper.mergePresenciaIdAndPresenciaDtoToPresencia(id, presenciaDto);
		Presencia presenciaSaved = presenciaRepository.save(presencia);
		PresenciaDto presenciaDtoDevuelta = presenciaMapper.presenciaToPresenciaDto(presenciaSaved);

		return presenciaDtoDevuelta;
	}

}
