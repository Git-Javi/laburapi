package app.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "PRESENCIA")
public class Presencia {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@PastOrPresent
	@NonNull
	@Column(name = "fecha_hora_inicio_presencia", nullable = false)
	private LocalDateTime inicio;
	
	@PastOrPresent
	@Column(name = "fecha_hora_fin_presencia")
	private LocalDateTime fin;
	
	@ToString.Exclude
	@NonNull
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "id_persona", nullable = false)
	private Persona persona;
		
}
