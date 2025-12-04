package SistemasDistribuidos.Servicios.Impl;

import SistemasDistribuidos.Entidades.Clase;
import SistemasDistribuidos.Entidades.Dto.ClaseDto;
import SistemasDistribuidos.Entidades.Dto.InscripcionDto;
import SistemasDistribuidos.Entidades.Dto.InscriptoDto;
import SistemasDistribuidos.Entidades.Enums.EstadoClaseEnum;
import SistemasDistribuidos.Entidades.Enums.EstadoInscripcion;
import SistemasDistribuidos.Entidades.Inscriptos;
import SistemasDistribuidos.Entidades.Usuario;
import SistemasDistribuidos.Exepciones.InscripcionNoDisponibleExceptcion;
import SistemasDistribuidos.Exepciones.RecursoNoEncontradoExcepcion;
import SistemasDistribuidos.Exepciones.SinCuposDisponiblesException;
import SistemasDistribuidos.Exepciones.UsuarioYaInscriptoExcepcion;
import SistemasDistribuidos.Mapper.ClaseMapper;
import SistemasDistribuidos.Mapper.InscripcionMapper;
import SistemasDistribuidos.Repositorios.ClaseRepositorio;
import SistemasDistribuidos.Repositorios.InscriptosRepositorio;
import SistemasDistribuidos.Repositorios.UsuarioRepositorio;
import SistemasDistribuidos.Servicios.InscripcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InscripcionServicioImpl implements InscripcionServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private InscriptosRepositorio inscriptosRepositorio;
    @Autowired
    private ClaseRepositorio claseRepositorio;
    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Autowired
    private ClaseMapper claseMapper;


    @Transactional
    @Override
    public InscripcionDto incribirUsuario(String auth0Id ,Long idClase) {
       Long idUser= usuarioRepositorio.findByAuth0Id(auth0Id).getId();
       Usuario usuario= usuarioRepositorio.findById(idUser)
               .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro al usuario con ID: "+idUser));

       Clase clase = claseRepositorio.findById(idClase)
               .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro la clase con ID: "+idClase));

       if(clase.getEstadoClase().equals(EstadoClaseEnum.INACTIVA) ){
           throw new InscripcionNoDisponibleExceptcion("Inscripciones no disponibles");
       }

       if(inscriptosRepositorio.existsByUsuarioIdAndClaseIdAndEstadoInscripcion(idUser, idClase, EstadoInscripcion.ACTIVA)){
           throw new UsuarioYaInscriptoExcepcion("El usuario ya esta inscripto en esta clase");
       }

       if(clase.getCupoDisponible()<=0){
           throw new SinCuposDisponiblesException("No hay cupo disponible para esta clase");
       }

       Inscriptos inscripcion= Inscriptos.builder()
               .usuario(usuario)
               .clase(clase)
               .estadoInscripcion(EstadoInscripcion.ACTIVA)
               .build();

        inscripcion= inscriptosRepositorio.save(inscripcion);

        clase.setCupoDisponible(clase.getCupoDisponible()-1);
        claseRepositorio.save(clase);

       return inscripcionMapper.toInscripcionDto(inscripcion);

    }


    @Transactional
    @Override
    public void anularInscripcion(String auth0Id ,Long idClase) {
        Long idUser= usuarioRepositorio.findByAuth0Id(auth0Id).getId();
        Inscriptos inscripcion= inscriptosRepositorio.findByUsuarioIdAndClaseIdAndEstadoInscripcion(
                idUser, idClase, EstadoInscripcion.ACTIVA).orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro inscripcion activa a esta clase"));

        inscripcion.setEstadoInscripcion(EstadoInscripcion.CANCELADA);
        inscriptosRepositorio.save(inscripcion);

        Clase clase=inscripcion.getClase();
        clase.setCupoDisponible(clase.getCupoDisponible()+1);
        claseRepositorio.save(clase);
    }

    @Override
    public List<ClaseDto> obtenerClasesDelUsuario(String auth0Id) {
        Long idUsuario= usuarioRepositorio.findByAuth0Id(auth0Id).getId();
        List<Clase> clases= inscriptosRepositorio.findByUsuarioIdAndEstadoInscripcion(idUsuario,EstadoInscripcion.ACTIVA)
                .stream()
                .map(Inscriptos::getClase)
                .toList();
        return claseMapper.toClaseDtoList(clases);
    }

    @Override
    public List<InscriptoDto> obtenerUsuariosInscriptos(Long idClase) {
        return  inscriptosRepositorio.findByClaseIdWithUsuario(idClase);

    }

}
