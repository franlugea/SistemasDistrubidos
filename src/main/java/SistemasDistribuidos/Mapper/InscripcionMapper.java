package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.ClaseDto;
import SistemasDistribuidos.Entity.Dto.InscripcionDto;
import SistemasDistribuidos.Entity.Dto.RolDto;
import SistemasDistribuidos.Entity.Dto.UsuarioDto;
import SistemasDistribuidos.Entity.Inscriptos;
import SistemasDistribuidos.Entity.Rol;
import SistemasDistribuidos.Entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {
    InscripcionDto toInscripcionDto(Inscriptos inscriptos);

    UsuarioDto toUsuarioDto(Usuario usuario);

    @Mapping(source = "diaSemana", target = "diaSemana", qualifiedByName = "enumToString")
    @Mapping(source = "estadoClase", target = "estadoClase", qualifiedByName = "enumToString")
    ClaseDto toClaseDto(Clase clase);

    @Mapping(source = "nombre", target = "nombre", qualifiedByName = "enumToString")
    RolDto toRolDto(Rol rol);
    Set<RolDto> toRolDtos(Set<Rol> roles);

    @Named("enumToString")
    default String enumToString(Enum<?> enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }
}
