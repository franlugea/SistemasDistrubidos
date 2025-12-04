package SistemasDistribuidos.Controlador;

import SistemasDistribuidos.Entidades.Dto.CrearUsuarioDto;
import SistemasDistribuidos.Servicios.Impl.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioServicioImpl usuarioServicioImpl;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Validated @RequestBody CrearUsuarioDto usuarioDto) {
        usuarioServicioImpl.registrarUsuario(usuarioDto);
       return ResponseEntity.ok("Usuario creado correctamente");
    }
}
