package SistemasDistribuidos.Service;

import SistemasDistribuidos.Entity.Dto.CrearUsuarioDto;
import SistemasDistribuidos.Entity.Enums.RoleEnum;
import SistemasDistribuidos.Entity.Rol;
import SistemasDistribuidos.Entity.Usuario;
import SistemasDistribuidos.Mapper.UsuarioMapper;
import SistemasDistribuidos.Repository.RoleRepository;
import SistemasDistribuidos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UsuarioMapper usuarioMapper;

    @Transactional
    public void registrarUsuario(CrearUsuarioDto usuarioDto) {
        if(userRepository.existsByAuth0Id(usuarioDto.auth0Id())){
            throw new UsernameNotFoundException("Ya existe el usuario con auth0Id: " + usuarioDto.auth0Id());
        }


        Usuario usuario= usuarioMapper.usuarioDtoToUsuario(usuarioDto);

        Set<Rol> roles = usuarioDto.roles().stream()
                .map(nombreRol -> {
                    RoleEnum roleEnum = RoleEnum.valueOf(nombreRol);
                    return roleRepository.findFirstByNombre(roleEnum);
                })
                .collect(Collectors.toSet());

        usuario.setRoles(roles);

        userRepository.save(usuario);
        
    }

}
