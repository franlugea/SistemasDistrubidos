package SistemasDistribuidos.Mapper;

import SistemasDistribuidos.Entity.ActividadFisica;
import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.ActividadFisicaDto;
import SistemasDistribuidos.Entity.Dto.ClaseDto;
import SistemasDistribuidos.Entity.Enums.ActividadFisicaEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClaseMapper {

    ClaseDto toClaseDto(Clase clase);

    List<ClaseDto> toClaseDtoList(List<Clase> clases);
     @Mapping(source = "nombre", target = "nombre", qualifiedByName = "mapEnumToString")
    ActividadFisicaDto toActividadFisicaDto(ActividadFisica actividadFisica);

    @Named("mapEnumToString")
    default String map(ActividadFisicaEnum enumValue) {
        return (enumValue != null) ? enumValue.name() : null;
    }
}
