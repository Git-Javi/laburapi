package app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import app.model.entity.Presencia;

public interface PresenciaRepository extends PagingAndSortingRepository<Presencia, Long> {

}
