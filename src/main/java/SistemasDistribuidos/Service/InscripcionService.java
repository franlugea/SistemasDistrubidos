package SistemasDistribuidos.Service;

import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.ClaseDto;
import SistemasDistribuidos.Entity.Dto.InscripcionDto;
import SistemasDistribuidos.Entity.Dto.InscriptoDto;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;
import SistemasDistribuidos.Entity.Enums.EstadoInscripcion;
import SistemasDistribuidos.Entity.Inscriptos;
import SistemasDistribuidos.Entity.Usuario;
import SistemasDistribuidos.Mapper.ClaseMapper;
import SistemasDistribuidos.Mapper.InscripcionMapper;
import SistemasDistribuidos.Repository.ClaseRepository;
import SistemasDistribuidos.Repository.InscriptosRepository;
import SistemasDistribuidos.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InscriptosRepository inscriptosRepository;
    @Autowired
    private ClaseRepository claseRepository;
    @Autowired
    private InscripcionMapper inscripcionMapper;

    @Autowired
    private ClaseMapper claseMapper;


    @Transactional
    public InscripcionDto incribirUsuario(String auth0Id ,Long idClase) {
       Long idUser= userRepository.findByAuth0Id(auth0Id).getId();
       Usuario usuario= userRepository.findById(idUser)
               .orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));

       Clase clase = claseRepository.findById(idClase)
               .orElseThrow(()-> new EntityNotFoundException("Clase no encontrado"));

       if(clase.getEstadoClase()!= EstadoClaseEnum.ACTIVA){
           throw new EntityNotFoundException("Inscripciones no disponibles");
       }

       if(inscriptosRepository.existsByUsuarioIdAndClaseIdAndEstadoInscripcion(idUser, idClase, EstadoInscripcion.ACTIVA)){
           throw new RuntimeException("El usuario ya esta inscripto en esta clase");
       }

       if(clase.getCupo_disponible()<=0){
           throw new RuntimeException("No hay cupo disponible");
       }

       Inscriptos inscripcion= Inscriptos.builder()
               .usuario(usuario)
               .clase(clase)
               .estadoInscripcion(EstadoInscripcion.ACTIVA)
               .build();

        inscripcion=inscriptosRepository.save(inscripcion);

       actualizarCupo(clase);

       return inscripcionMapper.toInscripcionDto(inscripcion);

    }

    private void actualizarCupo(Clase clase){
        clase.setCupo_disponible(clase.getCupo_disponible()-1);
        claseRepository.save(clase);
    }

    @Transactional
    public void anularInscripcion(String auth0Id ,Long idClase) {
        Long idUser= userRepository.findByAuth0Id(auth0Id).getId();
        Inscriptos inscripcion= inscriptosRepository.findByUsuarioIdAndClaseIdAndEstadoInscripcion(
                idUser, idClase, EstadoInscripcion.ACTIVA).orElseThrow(()-> new EntityNotFoundException("Inscripcion no encontrado"));

        inscripcion.setEstadoInscripcion(EstadoInscripcion.CANCELADA);
        inscriptosRepository.save(inscripcion);

        Clase clase=inscripcion.getClase();
        clase.setCupo_disponible(clase.getCupo_disponible()+1);
        claseRepository.save(clase);
    }


    public List<ClaseDto> obtenerClasesDelUsuario(String auth0Id) {
        Long idUsuario= userRepository.findByAuth0Id(auth0Id).getId();
        List<Clase> clases= inscriptosRepository.findByUsuarioIdAndEstadoInscripcion(idUsuario,EstadoInscripcion.ACTIVA)
                .stream()
                .map(Inscriptos::getClase)
                .toList();
        return claseMapper.toClaseDtoList(clases);
    }

    public List<InscriptoDto> obtenerUsuariosInscriptos(Long idClase) {
        return  inscriptosRepository.findByClaseIdWithUsuario(idClase);

    }

}
