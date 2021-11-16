package app.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Detalles sobre la entidad: ")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPresenciaDto {
	
	@ApiModelProperty(value = "El id del Tipo de Presencia", required = false, accessMode = AccessMode.READ_ONLY, position = 1)
	private Long id;
	
	@ApiModelProperty(value = "El nombre del Tipo de Presencia", required = true, accessMode = AccessMode.READ_ONLY, position = 2)
	@NotBlank(message = "Debe indicar el nombre del Tipo de Presencia.")
	@NotNull
	private String nombre;
}