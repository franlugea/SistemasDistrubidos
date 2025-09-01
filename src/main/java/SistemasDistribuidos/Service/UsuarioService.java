package SistemasDistribuidos.Service;

import SistemasDistribuidos.Entity.Dto.CrearUsuarioDto;
import SistemasDistribuidos.Entity.Dto.UsuarioDto;
import SistemasDistribuidos.Entity.Enums.RoleEnum;
import SistemasDistribuidos.Entity.Rol;
import SistemasDistribuidos.Entity.Usuario;
import SistemasDistribuidos.Repository.RoleRepository;
import SistemasDistribuidos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;


    public void registrarUsuario(CrearUsuarioDto usuarioDto) {
        if(userRepository.existsByAuth0Id(usuarioDto.auth0Id())){
            throw new UsernameNotFoundException("Ya existe el usuario con auth0Id: " + usuarioDto.auth0Id());
        }

        Set<Rol> roles = roleRepository.findByNombreIn(
                mapearRoles(usuarioDto.roles())
        );
        Usuario usuario =Usuario
                .builder()
                .auth0Id(usuarioDto.auth0Id())
                .nombre(usuarioDto.nombre())
                .email(usuarioDto.email())
                .roles(roles)
                .build();

        userRepository.save(usuario);
        
    }

    private List<RoleEnum> mapearRoles(List<String> rolesString) {
        return rolesString.stream()
                .map(role -> switch(role.toLowerCase()) {
                    case "admin" -> RoleEnum.ADMIN;
                    case "user" -> RoleEnum.USER;
                    default -> RoleEnum.USER; // Por defecto USER
                })
                .toList();
    }
}
