package app.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {

	private Long id;

	private String dni;

	private String nombre;

	private String telefono;

}
