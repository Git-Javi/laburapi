package app.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import app.api.dto.PresenciaDto;

public interface PresenciaService {

	public PresenciaDto createPresencia(@NotNull @Valid PresenciaDto presencia);

	public PresenciaDto findPresenciaById(@NotNull @Positive Long id);

	public PresenciaDto updatePresenciaById(@NotNull @Positive Long id, @NotNull @Valid PresenciaDto presencia);

	public void deletePresenciaById(@NotNull @Positive Long id);

	public List<PresenciaDto> findPresencias();
	
	public PresenciaDto updatePresenciaFieldsById(@NotNull @Positive Long id, Map<String,Object> fields);
	
	public List<PresenciaDto> findPresenciasByPersonaId(@NotNull @Positive Long personaId);

	public void presenciaExists(@NotNull @Positive Long id);

	public PresenciaDto presenciaFind(@NotNull @Positive Long id);

	public PresenciaDto presenciaMergeIdSave(@NotNull @Positive Long id, @NotNull @Valid PresenciaDto presenciaDto);

}
