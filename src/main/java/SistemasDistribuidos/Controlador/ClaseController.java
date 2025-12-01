package SistemasDistribuidos.Controlador;

import SistemasDistribuidos.Entity.Dto.CrearClaseDto;
import SistemasDistribuidos.Entity.Dto.EditarClaseDto;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;
import SistemasDistribuidos.Service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;


    @GetMapping
    private ResponseEntity<?> obtenerTodasLasClases(Authentication auth) {
        return ResponseEntity.ok(claseService.obtenerTodasLasClases(auth));
    }


    @PostMapping
    private ResponseEntity<?> crearClase(@RequestBody CrearClaseDto crearClaseDto) {
        return ResponseEntity.ok(claseService.crearClase(crearClaseDto));
    }

    @PatchMapping("{id}")
    private ResponseEntity<?> editarClase(@PathVariable Long id,@RequestBody EditarClaseDto editarClaseDto) {
        return ResponseEntity.ok(claseService.editarClase(id,editarClaseDto));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminarClase(@PathVariable Long id){
        claseService.eliminarClase(id);
        return ResponseEntity.ok("Clase eliminada con exito");
    }


}
