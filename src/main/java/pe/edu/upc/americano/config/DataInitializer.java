package pe.edu.upc.americano.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pe.edu.upc.americano.entities.Usuario;
import pe.edu.upc.americano.enums.TipoUsuario;
import pe.edu.upc.americano.repository.RepositoryUsuario;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RepositoryUsuario repositoryUsuario;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si ya existen usuarios por defecto
        if (repositoryUsuario.findAll().isEmpty()) {
            
            // Crear usuario Admin
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setNombre("Enrique");
            admin.setApellido("Paredes");
            admin.setTipoUsuario(TipoUsuario.ADMIN);
            admin.setPassword("admin");
            repositoryUsuario.save(admin);
            
            // Crear usuario Emisor
            Usuario emisor = new Usuario();
            emisor.setUsername("emisor");
            emisor.setNombre("Luis");
            emisor.setApellido("Ramírez");
            emisor.setTipoUsuario(TipoUsuario.EMISOR);
            emisor.setPassword("emisor");
            repositoryUsuario.save(emisor);
            
            // Crear usuario Bonista
            Usuario bonista = new Usuario();
            bonista.setUsername("bonista");
            bonista.setNombre("Cristian");
            bonista.setApellido("Cuyubamba");
            bonista.setTipoUsuario(TipoUsuario.BONISTA);
            bonista.setPassword("bonista");
            repositoryUsuario.save(bonista);
            
            System.out.println("=== Usuarios por defecto creados ===");
            System.out.println("ADMIN - Username: admin, Password: admin (Enrique Paredes)");
            System.out.println("EMISOR - Username: emisor, Password: emisor (Luis Ramírez)");
            System.out.println("BONISTA - Username: bonista, Password: bonista (Cristian Cuyubamba)");
        }
    }
}
