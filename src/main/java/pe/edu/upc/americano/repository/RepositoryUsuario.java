package pe.edu.upc.americano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.americano.entities.Usuario;

@Repository
public interface RepositoryUsuario extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
}
