package app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "PERSONA")
public class Persona {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@NonNull
	@Column(name = "dni", nullable = false)
	private String dni;

	@NonNull
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@NonNull
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	
}
