package SistemasDistribuidos.Controlador;

import SistemasDistribuidos.Entity.Dto.InscripcionDto;
import SistemasDistribuidos.Service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping("/{idClase}/inscribir")
    public ResponseEntity<?> inscribirUsuario(@PathVariable Long idClase, @AuthenticationPrincipal Jwt jwt){
        String auth0Id= "auth0|123"; //jwt.getClaimAsString("sub");
        return ResponseEntity.ok(inscripcionService.incribirUsuario(auth0Id,idClase));
    }

    @PostMapping("/{idClase}/anular")
    public ResponseEntity<?> anularInscripcion(@PathVariable Long idClase,@AuthenticationPrincipal Jwt jwt) {
        String auth0Id="auth0|123"; //jwt.getClaimAsString("sub");
        inscripcionService.anularInscripcion(auth0Id,idClase);
        return ResponseEntity.ok("Inscripcion anulada exitosamente");
    }

    @GetMapping("/{idClase}")
    public ResponseEntity<?> obtenerUsuariosInscriptos(@PathVariable Long idClase) {
        return ResponseEntity.ok(inscripcionService.obtenerUsuariosInscriptos(idClase));
    }

    @GetMapping("/clases")
    public ResponseEntity<?> obtenerClasesDelUsuario(@AuthenticationPrincipal Jwt jwt){
        String auth0Id= "auth0|123"; //jwt.getClaimAsString("sub");
        return ResponseEntity.ok(inscripcionService.obtenerClasesDelUsuario(auth0Id));
    }

    
}
