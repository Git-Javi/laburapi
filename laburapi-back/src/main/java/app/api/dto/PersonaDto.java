package app.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

	private Long id;

	@NotBlank(message = "Debe indicar el DNI de la persona.")
	@NotNull
	private String dni;

	@NotBlank(message = "Debe indicar el nombre de la persona.")
	@NotNull
	private String nombre;

	@NotBlank(message = "Debe indicar el teléfono de la persona.")
	@NotNull
	private String telefono;

}
