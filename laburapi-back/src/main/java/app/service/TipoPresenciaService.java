package app.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import app.api.dto.TipoPresenciaDto;

public interface TipoPresenciaService {

	public TipoPresenciaDto createTipoPresencia(@NotNull @Valid TipoPresenciaDto tipoPresencia);

	public TipoPresenciaDto findTipoPresenciaById(@NotNull @Positive Long id);

	public TipoPresenciaDto updateTipoPresenciaById(@NotNull @Positive Long id, @NotNull @Valid TipoPresenciaDto tipoPresencia);

	public void deleteTipoPresenciaById(@NotNull @Positive Long id);

	public List<TipoPresenciaDto> findTipoPresencias();

	public TipoPresenciaDto updateTipoPresenciaFieldsById(@NotNull @Positive Long id, Map<String, Object> fields);

	public void tipoPresenciaExists(@NotNull @Positive Long id);

	public TipoPresenciaDto tipoPresenciaFind(@NotNull @Positive Long id);

	public TipoPresenciaDto tipoPresenciaMergeIdSave(@NotNull @Positive Long id, @NotNull @Valid TipoPresenciaDto tipoPresenciaDto);

}