package pe.edu.upc.americano.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.americano.dtos.UsuarioDTO;
import pe.edu.upc.americano.entities.Usuario;
import pe.edu.upc.americano.serviceinter.serviceinterUsuario;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class ControllerUsuario {
    @Autowired
    private serviceinterUsuario userservice;
    //insertar
    @PostMapping("/insertar")
    public void insertar(@RequestBody UsuarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        userservice.insertarUsuario(usuario);
    }

    //modificar
    @PutMapping("/edit")
    public void editar(@RequestBody UsuarioDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        userservice.updateUsuario(usuario);

    }

    //delete
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        userservice.eliminarUsuario(id);
    }

    //listar
    @GetMapping("/lista")
    public List<UsuarioDTO> List() {
        return userservice.listarUsuario().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }
    //buscar por id
    @GetMapping("/buscaid/{id}")
    public UsuarioDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(userservice.listId(id), UsuarioDTO.class);
        return dto;
    }
    //login endpoint
    @PostMapping("/login")
    public UsuarioDTO login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = userservice.findByUsername(loginRequest.getUsername());
        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())) {
            ModelMapper m = new ModelMapper();
            return m.map(usuario, UsuarioDTO.class);
        }
        return null;
    }

    // Inner class for login request
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
