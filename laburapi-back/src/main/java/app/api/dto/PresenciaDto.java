package app.api.dto;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
public class PresenciaDto {

	@ApiModelProperty(value = "El id de la Presencia", required = false, accessMode = AccessMode.READ_ONLY, position = 1)
	private Long id;
	
	@ApiModelProperty(value = "La hora de incio de la Presencia", required = true, accessMode = AccessMode.READ_ONLY, position = 2)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@PastOrPresent(message = "El instante de inicio no puede ser futuro")
	@DateTimeFormat
	@NotNull
	private LocalDateTime inicio;
	
	@ApiModelProperty(value = "La hora de fin de la Presencia", required = false, accessMode = AccessMode.READ_ONLY, position = 3)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@PastOrPresent(message = "El instante de inicio no puede ser futuro")
	@DateTimeFormat
	@Nullable
	private LocalDateTime fin;
	
	@Valid
	@NotNull
	@ApiModelProperty(value = "La Persona que ha registrado la Presencia", required = true, accessMode = AccessMode.READ_ONLY, position = 4)
	private PersonaDto persona;
	
	@Valid
	@NotNull
	@ApiModelProperty(value = "El el Tipo de la Presencia", required = true, accessMode = AccessMode.READ_ONLY, position = 5)
	private TipoPresenciaDto tipoPresencia;

}
