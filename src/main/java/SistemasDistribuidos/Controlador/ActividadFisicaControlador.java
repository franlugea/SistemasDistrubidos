package SistemasDistribuidos.Controlador;


import SistemasDistribuidos.Servicios.Impl.ActividadFisicaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/actividades")
public class ActividadFisicaControlador {

    @Autowired
    private ActividadFisicaServicioImpl actividadFisicaServicioImpl;

    @GetMapping
    public ResponseEntity<?> obtenerActividades(){
        return ResponseEntity.ok(actividadFisicaServicioImpl.obtenerActividades());
    }
}
