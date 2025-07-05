package pe.edu.upc.americano.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.americano.dtos.ReportesDTO;
import pe.edu.upc.americano.entities.Reportes;
import pe.edu.upc.americano.serviceinter.serviceinterReportes;
import pe.edu.upc.americano.serviceinter.serviceinterBono;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ControllerReportes {
    @Autowired
    private serviceinterReportes reportesservice;
    @Autowired
    private serviceinterBono bonoservice;
    //insertar
    @PostMapping("/insertar")
    public void insertar(@RequestBody ReportesDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Reportes reportes = modelMapper.map(dto, Reportes.class);
        reportesservice.insertarReporte(reportes);
    }

    //modificar
    @PutMapping("/edit")
    public void editar(@RequestBody ReportesDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Reportes reportes = modelMapper.map(dto, Reportes.class);
        reportesservice.updateReporte(reportes);

    }

    //delete
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") int id) {
        reportesservice.eliminarReporte(id);
    }

    //listar
    @GetMapping("/lista")
    public List<ReportesDTO> List() {
        return reportesservice.listarReporte().stream().map(x -> {
            ModelMapper m = new ModelMapper();
            return m.map(x, ReportesDTO.class);
        }).collect(Collectors.toList());
    }
    //buscar por id
    @GetMapping("/buscaid/{id}")
    public ReportesDTO buscarId(@PathVariable("id") int id) {
        ModelMapper m = new ModelMapper();
        ReportesDTO dto = m.map(reportesservice.listId(id), ReportesDTO.class);
        return dto;
    }
    
    //recalcular reporte por id de bono
    @PostMapping("/recalcular/{idBono}")
    public void recalcularReporte(@PathVariable("idBono") int idBono) {
        bonoservice.recalcularReporte(idBono);
    }
}
