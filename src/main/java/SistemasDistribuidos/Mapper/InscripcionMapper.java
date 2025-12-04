package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entidades.Clase;
import SistemasDistribuidos.Entidades.Dto.*;
import SistemasDistribuidos.Entidades.Inscriptos;
import SistemasDistribuidos.Entidades.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {

    UsuarioDto toUsuarioDto(Usuario usuario);

    @Mapping(source = "diaSemana", target = "diaSemana", qualifiedByName = "enumToString")
    @Mapping(source = "estadoClase", target = "estadoClase", qualifiedByName = "enumToString")
    ClaseDto toClaseDto(Clase clase);

    InscripcionDto toInscripcionDto(Inscriptos inscriptos);


    @Named("enumToString")
    default String enumToString(Enum<?> enumValue) {
        return enumValue != null ? enumValue.name() : null;
    }
}
