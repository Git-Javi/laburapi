package app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import app.model.entity.Persona;
import app.model.entity.Presencia;

public interface PresenciaRepository extends PagingAndSortingRepository<Presencia, Long>, JpaSpecificationExecutor<Persona> {
	
	@Query(value = "SELECT  * FROM PRESENCIA WHERE id_persona=?1", nativeQuery = true)
	public List<Presencia> findAllPresenciaByPersonaId(Long personaId);
}
