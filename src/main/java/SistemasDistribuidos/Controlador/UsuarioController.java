package SistemasDistribuidos.Controlador;

import SistemasDistribuidos.Entity.Dto.CrearUsuarioDto;
import SistemasDistribuidos.Entity.Dto.UsuarioDto;
import SistemasDistribuidos.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/crearUsuario")
    public ResponseEntity<?> crearUsuario(@RequestBody CrearUsuarioDto usuarioDto) {
        usuarioService.registrarUsuario(usuarioDto);
       return ResponseEntity.ok("Usuario creado correctamente");
    }
}
