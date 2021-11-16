package app.mapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

import app.api.dto.TipoPresenciaDto;
import app.model.entity.TipoPresencia;

@Validated
@Mapper
public interface TipoPresenciaMapper {

	TipoPresenciaDto tipoPresenciaToTipoPresenciaDto(@Valid @NotNull TipoPresencia tipoPresencia);

	TipoPresencia tipoPresenciaDtoToTipoPresencia(@Valid @NotNull TipoPresenciaDto tipoPresenciaDto);

	@Mapping(source = "tipoPresenciaId", target = "id")
	TipoPresencia mergeTipoPresenciaIdAndTipoPresenciaDtoToTipoPresencia(
			@NotNull @Positive Long tipoPresenciaId,
			@Valid @NotNull TipoPresenciaDto tipoPresenciaDTo);
}