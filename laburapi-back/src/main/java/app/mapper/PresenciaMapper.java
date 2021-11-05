package app.mapper;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

import app.api.dto.PresenciaDto;
import app.model.entity.Presencia;

@Validated
@Mapper
public interface PresenciaMapper {

	PresenciaDto presenciaToPresenciaDto (@Valid @NotNull Presencia presencia);
		
	Presencia presenciaDtoToPresencia (@Valid @NotNull PresenciaDto presenciaDto);
		
	@Mapping(source = "presenciaId", target = "id")
	Presencia mergePresenciaIdAndPresenciaDtoToPresencia (@NotNull @Positive Long presenciaId, @Valid @NotNull PresenciaDto presenciaDTo);
}
