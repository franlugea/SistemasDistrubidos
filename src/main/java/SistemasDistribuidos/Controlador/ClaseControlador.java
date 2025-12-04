package SistemasDistribuidos.Controlador;

import SistemasDistribuidos.Entidades.Dto.CrearClaseDto;
import SistemasDistribuidos.Entidades.Dto.EditarClaseDto;
import SistemasDistribuidos.Servicios.Impl.ClaseServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clases")
public class ClaseControlador {

    @Autowired
    private ClaseServicioImpl claseServicioImpl;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<?> obtenerTodasLasClases() {
        return ResponseEntity.ok(claseServicioImpl.obtenerTodasLasClases());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/usuario")
    public ResponseEntity<?> obtenerClasesUsuario(){
        return ResponseEntity.ok(claseServicioImpl.obtenerClasesDisponibles());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> crearClase(@Validated @RequestBody CrearClaseDto crearClaseDto) {
        return ResponseEntity.ok(claseServicioImpl.crearClase(crearClaseDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("{id}")
    public ResponseEntity<?> editarClase(@PathVariable Long id,@Validated @RequestBody EditarClaseDto editarClaseDto) {
        return ResponseEntity.ok(claseServicioImpl.editarClase(id,editarClaseDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarClase(@PathVariable Long id){
        claseServicioImpl.eliminarClase(id);
        return ResponseEntity.ok("Clase eliminada con exito");
    }


}
