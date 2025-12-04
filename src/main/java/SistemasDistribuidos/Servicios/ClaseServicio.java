package SistemasDistribuidos.Servicios;

import SistemasDistribuidos.Entidades.Dto.ClaseDto;
import SistemasDistribuidos.Entidades.Dto.CrearClaseDto;
import SistemasDistribuidos.Entidades.Dto.EditarClaseDto;

import java.util.List;

public interface ClaseServicio {

     List<ClaseDto> obtenerTodasLasClases();

     List<ClaseDto> obtenerClasesDisponibles();

     ClaseDto crearClase(CrearClaseDto claseDto);

     List<ClaseDto> editarClase(Long id, EditarClaseDto dto);

     void eliminarClase(Long id);
}
