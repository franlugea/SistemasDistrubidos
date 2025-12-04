package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entidades.ActividadFisica;
import SistemasDistribuidos.Entidades.Clase;
import SistemasDistribuidos.Entidades.Dto.*;
import SistemasDistribuidos.Entidades.Enums.ActividadFisicaEnum;
import SistemasDistribuidos.Entidades.Inscriptos;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ClaseMapper {

    @Mapping(target = "cupoMaximo", source = "cupoMaximo")
    @Mapping(target = "cupoDisponible", source = "cupoDisponible")
    ClaseDto toClaseDto(Clase clase);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cupoMaximo", source = "cupoMaximo")
    @Mapping(target = "cupoDisponible", source = "cupoMaximo")
    @Mapping(target = "actividadFisica", ignore = true)
    @Mapping(target = "inscripciones", ignore = true)
    Clase toEntity(CrearClaseDto dto);


    List<InscriptoDto> toInscriptosDto(Set<Inscriptos> inscripciones);

    List<ClaseDto> toClaseDtoList(List<Clase> clases);

     @Mapping(source = "nombre", target = "nombre", qualifiedByName = "mapEnumToString")
    ActividadFisicaDto toActividadFisicaDto(ActividadFisica actividadFisica);

     List<ActividadFisicaDto> toActividadFisicaDtoList(List<ActividadFisica> actividades);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cupoDisponible", ignore = true)
    @Mapping(target = "actividadFisica", ignore = true)
    @Mapping(target = "inscripciones", ignore = true)
     void editarClase(EditarClaseDto editarClaseDto, @MappingTarget Clase clase);

    @Named("mapEnumToString")
    default String map(ActividadFisicaEnum enumValue) {
        return (enumValue != null) ? enumValue.name() : null;
    }
}
