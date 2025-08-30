package SistemasDistribuidos.Controlador;

import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.ClaseDto;
import SistemasDistribuidos.Service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/private/clase")
public class ClaseController {

    @Autowired
    private ClaseService claseService;


    @GetMapping("/buscarClases")
    private List<ClaseDto> obtenerTodasLasClases() {
        return claseService.obtenerTodasLasClases();
    }
}
