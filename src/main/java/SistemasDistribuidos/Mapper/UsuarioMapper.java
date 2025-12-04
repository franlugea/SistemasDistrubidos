package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entidades.Dto.CrearUsuarioDto;
import SistemasDistribuidos.Entidades.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

 @Mapping(target = "id", ignore = true)
 @Mapping(target = "roles", ignore = true)
 Usuario usuarioDtoToUsuario(CrearUsuarioDto crearUsuarioDto);
}
