package SistemasDistribuidos.Servicios;

import SistemasDistribuidos.Entidades.Dto.ClaseDto;
import SistemasDistribuidos.Entidades.Dto.InscripcionDto;
import SistemasDistribuidos.Entidades.Dto.InscriptoDto;

import java.util.List;

public interface InscripcionServicio {

     InscripcionDto incribirUsuario(String auth0Id , Long idClase);

     void anularInscripcion(String auth0Id ,Long idClase);

     List<ClaseDto> obtenerClasesDelUsuario(String auth0Id);

     List<InscriptoDto> obtenerUsuariosInscriptos(Long idClase);
}
