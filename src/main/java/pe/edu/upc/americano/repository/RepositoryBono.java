package pe.edu.upc.americano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.americano.entities.Bono;
import java.util.List;

@Repository
public interface RepositoryBono extends JpaRepository<Bono,Integer> {
    Bono findFirstByUsuario_IdUsuarioOrderByIdBonoDesc(int idUsuario);
    
    // Encontrar bonos por estado
    List<Bono> findByEstado(String estado);
    
    // Encontrar bonos por usuario y estado
    List<Bono> findByUsuario_IdUsuarioAndEstado(int idUsuario, String estado);
    
    // Encontrar bonos por usuario
    List<Bono> findByUsuario_IdUsuario(int idUsuario);
    
    // Encontrar bonos por emisor
    List<Bono> findByEmisor_IdUsuario(int idEmisor);
}
