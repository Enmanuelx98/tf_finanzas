package pe.edu.upc.americano.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.americano.dtos.BonoDTO;
import pe.edu.upc.americano.entities.Bono;
import pe.edu.upc.americano.serviceinter.serviceinterBono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bono")
public class ControllerBono {
    @Autowired
    private serviceinterBono bonoService;
    //insertar
    @PostMapping("/insertar")
    public void insertar(@RequestBody BonoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Bono bono = modelMapper.map(dto, Bono.class);
        bonoService.insertarBono(bono);
    }

    //modificar
    @PutMapping("/edit")
    public void editar(@RequestBody BonoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Bono bono = modelMapper.map(dto, Bono.class);
        bonoService.updateBono(bono);

    }

    //delete
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        bonoService.eliminarBono(id);
    }

    //listar
    @GetMapping("/lista")
    public List<BonoDTO> List() {
        return bonoService.listarBono().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BonoDTO.class);
        }).collect(Collectors.toList());
    }
    //buscar por id
    @GetMapping("/buscaid/{id}")
    public BonoDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        BonoDTO dto = m.map(bonoService.listId(id), BonoDTO.class);
        return dto;
    }

    // Nuevos endpoints para estados
    @GetMapping("/estado/{estado}")
    public List<BonoDTO> listarPorEstado(@PathVariable("estado") String estado) {
        return bonoService.listarBonosPorEstado(estado).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BonoDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/usuario/{idUsuario}/estado/{estado}")
    public List<BonoDTO> listarPorUsuarioYEstado(@PathVariable("idUsuario") int idUsuario, @PathVariable("estado") String estado) {
        return bonoService.listarBonosPorUsuarioYEstado(idUsuario, estado).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BonoDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<BonoDTO> listarPorUsuario(@PathVariable("idUsuario") int idUsuario) {
        return bonoService.listarBonosPorUsuario(idUsuario).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BonoDTO.class);
        }).collect(Collectors.toList());
    }

    @GetMapping("/emisor/{idEmisor}")
    public List<BonoDTO> listarPorEmisor(@PathVariable("idEmisor") int idEmisor) {
        return bonoService.listarBonosPorEmisor(idEmisor).stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, BonoDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/comprar/{idBono}/{idBonista}")
    public void comprarBono(@PathVariable("idBono") int idBono, @PathVariable("idBonista") int idBonista) {
        bonoService.comprarBono(idBono, idBonista);
    }
}
