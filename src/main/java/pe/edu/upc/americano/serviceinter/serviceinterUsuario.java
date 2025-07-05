package pe.edu.upc.americano.serviceinter;

import pe.edu.upc.americano.entities.Usuario;

import java.util.List;

public interface serviceinterUsuario {
    public void insertarUsuario(Usuario u);
    public void updateUsuario(Usuario u);
    public void eliminarUsuario(int id);
    public List<Usuario> listarUsuario();
    public Usuario listId(int id);
    public Usuario findByUsername(String username);
}
