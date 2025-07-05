package pe.edu.upc.americano.serviceinter;
import pe.edu.upc.americano.entities.Reportes;
import java.util.List;

public interface serviceinterReportes {
    public void insertarReporte(Reportes r);
    public void updateReporte(Reportes r);
    public void eliminarReporte(int id);
    public List<Reportes> listarReporte();
    public Reportes listId(int id);
}
