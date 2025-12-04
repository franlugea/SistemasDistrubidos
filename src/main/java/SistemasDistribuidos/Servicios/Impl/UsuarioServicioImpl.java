package SistemasDistribuidos.Servicios.Impl;

import SistemasDistribuidos.Entidades.Dto.CrearUsuarioDto;
import SistemasDistribuidos.Entidades.Enums.RolEnum;
import SistemasDistribuidos.Entidades.Rol;
import SistemasDistribuidos.Entidades.Usuario;
import SistemasDistribuidos.Mapper.UsuarioMapper;
import SistemasDistribuidos.Repositorios.RolRepositorio;
import SistemasDistribuidos.Repositorios.UsuarioRepositorio;
import SistemasDistribuidos.Servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    RolRepositorio rolRepositorio;
    @Autowired
    UsuarioMapper usuarioMapper;

    @Transactional
    @Override
    public void registrarUsuario(CrearUsuarioDto usuarioDto) {
        if(usuarioRepositorio.existsByAuth0Id(usuarioDto.auth0Id())){
            this.actualizarInformacionUsuario(usuarioDto);
        }


        Usuario usuario= usuarioMapper.usuarioDtoToUsuario(usuarioDto);

        usuario.setRoles(this.mapearRoles(usuarioDto.roles()));

        usuarioRepositorio.save(usuario);
        
    }

    @Transactional
    protected void actualizarInformacionUsuario(CrearUsuarioDto usuarioDto) {
        Usuario usuario = usuarioRepositorio.findByAuth0Id(usuarioDto.auth0Id());
        boolean actualizacion=false;

        if(!Objects.equals(usuario.getNombre(), usuarioDto.nombre())){
            usuario.setNombre(usuarioDto.nombre());
            actualizacion=true;
        }
        if(!Objects.equals(usuario.getEmail(), usuarioDto.email())){
            usuario.setEmail(usuarioDto.email());
            actualizacion=true;
        }

        Set<Rol> roles = this.mapearRoles(usuarioDto.roles());
        if(!Objects.equals(usuario.getRoles(), roles)){
            usuario.setRoles(roles);
            actualizacion=true;
        }

        if(actualizacion){
            usuarioRepositorio.save(usuario);
        }
    }

    private Set<Rol> mapearRoles(List<String> roles) {
        return roles.stream()
                .map(nombreRol -> {
                    RolEnum rolEnum = RolEnum.valueOf(nombreRol);
                    return rolRepositorio.findByNombre(rolEnum);
                })
                .collect(Collectors.toSet());
    }

}
