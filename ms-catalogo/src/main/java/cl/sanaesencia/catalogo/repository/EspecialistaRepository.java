package cl.sanaesencia.catalogo.repository;

import cl.sanaesencia.catalogo.model.Especialista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialistaRepository extends JpaRepository<Especialista, Integer> {
}
