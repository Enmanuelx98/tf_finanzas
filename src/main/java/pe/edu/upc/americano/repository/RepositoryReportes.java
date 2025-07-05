package pe.edu.upc.americano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.americano.entities.Reportes;

@Repository
public interface RepositoryReportes extends JpaRepository<Reportes, Integer> {
    void deleteByIdbono(int idBono);
}
