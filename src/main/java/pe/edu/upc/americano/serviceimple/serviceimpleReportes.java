package pe.edu.upc.americano.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.americano.entities.Reportes;
import pe.edu.upc.americano.repository.RepositoryReportes;
import pe.edu.upc.americano.serviceinter.serviceinterReportes;

import java.util.List;

@Service
public class serviceimpleReportes implements serviceinterReportes {
    @Autowired
    private RepositoryReportes repositoryReportes;
    @Override
    public void insertarReporte(Reportes r) {
        repositoryReportes.save(r);
    }

    @Override
    public void updateReporte(Reportes r) {
        repositoryReportes.save(r);
    }

    @Override
    public void eliminarReporte(int id) {
        repositoryReportes.deleteById(id);

    }

    @Override
    public List<Reportes> listarReporte() {
        return repositoryReportes.findAll();
    }

    @Override
    public Reportes listId(int id) {
        return repositoryReportes.findById(id).orElse(new Reportes());
    }

    public void eliminarReportePorIdBono(int idBono) {
        repositoryReportes.deleteByIdbono(idBono);
    }
}
