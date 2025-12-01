package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.*;
import SistemasDistribuidos.Entity.Inscriptos;
import SistemasDistribuidos.Entity.Rol;
import SistemasDistribuidos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {

    UsuarioDto toUsuarioDto(Usuario usuario);

    @Mapping(source = "diaSemana", target = "diaSemana", qualifiedByName = "enumToString")
    @Mapping(source = "estadoClase", target = "estadoClase", qualifiedByName = "enumToString")
    ClaseDto toClaseDto(Clase clase);

    InscripcionDto toInscripcionDto(Inscriptos inscriptos);

    /*@Mapping(source = "nombre", target = "nombre", qualifiedByName = "enumToString")
    RolDto toRolDto(Rol rol);
    Set<RolDto> toRolDtos(Set<Rol> roles);*/

    @Named("enumToString")
    default String enumToString(Enum<?> enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }
}
