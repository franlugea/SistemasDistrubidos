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
@RequestMapping("api/privada/inscripcion")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @PostMapping("inscribirUsuario/{idClase}")
    public ResponseEntity<?> inscribirUsuario(@PathVariable Long idClase, @AuthenticationPrincipal Jwt jwt){
        String auth0Id=jwt.getClaimAsString("sub");
        return ResponseEntity.ok(inscripcionService.incribirUsuario(auth0Id,idClase));
    }

    @DeleteMapping("anularInscripcion/{idClase}")
    public ResponseEntity<?> anularInscripcion(@PathVariable Long idClase,@AuthenticationPrincipal Jwt jwt) {
        String auth0Id=jwt.getClaimAsString("sub");
        inscripcionService.anularInscripcion(auth0Id,idClase);
        return ResponseEntity.ok("Inscripcion anulada exitosamente");
    }

    @GetMapping("mostrarInscriptos")
    public ResponseEntity<List<InscripcionDto>> mostrarInscriptos(){
        return ResponseEntity.ok(inscripcionService.mostrarInscripciones());
    }
}
