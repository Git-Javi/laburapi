package app.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Persona {

	private Long id;
	
	@NonNull
	private String dni;

	@NonNull
	private String nombre;

	@NonNull
	private String telefono;
	
}
