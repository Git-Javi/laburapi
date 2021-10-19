package app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import app.model.entity.Persona;

public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {

}
