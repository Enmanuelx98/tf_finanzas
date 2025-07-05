package pe.edu.upc.americano.serviceimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.americano.entities.Usuario;
import pe.edu.upc.americano.repository.RepositoryUsuario;
import pe.edu.upc.americano.serviceinter.serviceinterUsuario;

import java.util.List;

@Service
public class serviceimpleUsuario implements serviceinterUsuario {
    @Autowired
    private RepositoryUsuario repoUsuario;
    
    @Override
    public void insertarUsuario(Usuario u) {
        repoUsuario.save(u);
    }

    @Override
    public void updateUsuario(Usuario u) {
        repoUsuario.save(u);
    }

    @Override
    public void eliminarUsuario(int id) {
        repoUsuario.deleteById(id);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return repoUsuario.findAll();
    }

    @Override
    public Usuario listId(int id) {
        return repoUsuario.findById(id).orElse(new Usuario());
    }
    
    @Override
    public Usuario findByUsername(String username) {
        return repoUsuario.findByUsername(username);
    }
}
