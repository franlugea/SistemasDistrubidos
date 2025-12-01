package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entity.ActividadFisica;
import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.*;
import SistemasDistribuidos.Entity.Enums.ActividadFisicaEnum;
import SistemasDistribuidos.Entity.Inscriptos;
import SistemasDistribuidos.Entity.Usuario;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClaseMapper {

    ClaseDto toClaseDto(Clase clase);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cupo_maximo", source = "cupoMaximo")
    @Mapping(target = "horaInicio", source = "horaInicio")
    @Mapping(target = "horaFinal", source = "horaFinal")
    @Mapping(target = "cupo_disponible", source = "cupoMaximo")
    @Mapping(target = "actividadFisica", ignore = true)
    @Mapping(target = "inscripciones", ignore = true)
    Clase toEntity(CrearClaseDto dto);



    List<InscriptoDto> toInscriptosDto(Set<Inscriptos> inscripciones);

    List<ClaseDto> toClaseDtoList(List<Clase> clases);
     @Mapping(source = "nombre", target = "nombre", qualifiedByName = "mapEnumToString")
    ActividadFisicaDto toActividadFisicaDto(ActividadFisica actividadFisica);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cupo_maximo", source = "cupoMaximo")
    @Mapping(target = "cupo_disponible", ignore = true)
    @Mapping(target = "actividadFisica", ignore = true)
    @Mapping(target = "inscripciones", ignore = true)
     void editarClase(EditarClaseDto editarClaseDto, @MappingTarget Clase clase);

    @Named("mapEnumToString")
    default String map(ActividadFisicaEnum enumValue) {
        return (enumValue != null) ? enumValue.name() : null;
    }
}
