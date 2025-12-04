package SistemasDistribuidos.Controlador;

import SistemasDistribuidos.Servicios.Impl.InscripcionServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inscripcion")
public class InscripcionControlador {

    @Autowired
    private InscripcionServicioImpl inscripcionServicioImpl;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{idClase}/inscribir")
    public ResponseEntity<?> inscribirUsuario(@PathVariable Long idClase, @AuthenticationPrincipal Jwt jwt){
        String auth0Id= jwt.getClaimAsString("sub");
        return ResponseEntity.ok(inscripcionServicioImpl.incribirUsuario(auth0Id,idClase));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{idClase}/anular")
    public ResponseEntity<?> anularInscripcion(@PathVariable Long idClase,@AuthenticationPrincipal Jwt jwt) {
        String auth0Id=jwt.getClaimAsString("sub");
        inscripcionServicioImpl.anularInscripcion(auth0Id,idClase);
        return ResponseEntity.ok("Inscripcion anulada exitosamente");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{idClase}")
    public ResponseEntity<?> obtenerUsuariosInscriptos(@PathVariable Long idClase) {
        return ResponseEntity.ok(inscripcionServicioImpl.obtenerUsuariosInscriptos(idClase));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/clases")
    public ResponseEntity<?> obtenerClasesDelUsuario(@AuthenticationPrincipal Jwt jwt){
        String auth0Id= jwt.getClaimAsString("sub");
        return ResponseEntity.ok(inscripcionServicioImpl.obtenerClasesDelUsuario(auth0Id));
    }

    
}
