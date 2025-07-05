package pe.edu.upc.americano.serviceinter;
import pe.edu.upc.americano.entities.Bono;
import java.util.List;

public interface serviceinterBono {
    public void insertarBono(Bono b);
    public void updateBono(Bono b);
    public void eliminarBono(int id);
    public List<Bono> listarBono();
    public Bono listId(int id);
    Bono findFirstByUsuarioIdOrderByIdDesc(int idUsuario);
    public void recalcularReporte(int idBono);
    
    // Nuevos métodos para estados
    public List<Bono> listarBonosPorEstado(String estado);
    public List<Bono> listarBonosPorUsuarioYEstado(int idUsuario, String estado);
    public List<Bono> listarBonosPorUsuario(int idUsuario);
    public List<Bono> listarBonosPorEmisor(int idEmisor);
    public void comprarBono(int idBono, int idBonista);
}
