package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entity.Dto.CrearUsuarioDto;
import SistemasDistribuidos.Entity.Dto.UsuarioDto;
import SistemasDistribuidos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

 @Mapping(target = "id", ignore = true)
 @Mapping(target = "roles", ignore = true)
 Usuario usuarioDtoToUsuario(CrearUsuarioDto crearUsuarioDto);
}
