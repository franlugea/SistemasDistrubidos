package SistemasDistribuidos.Service;

import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.InscripcionDto;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;
import SistemasDistribuidos.Entity.Inscriptos;
import SistemasDistribuidos.Entity.Usuario;
import SistemasDistribuidos.Mapper.InscripcionMapper;
import SistemasDistribuidos.Repository.ClaseRepository;
import SistemasDistribuidos.Repository.InscriptosRepository;
import SistemasDistribuidos.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

       if(inscriptosRepository.existsByUsuario_IdAndClase_Id(idUser, idClase)){
           throw new RuntimeException("El usuario ya esta inscripto");
       }

       Inscriptos inscripcion= Inscriptos.builder()
               .usuario(usuario)
               .clase(clase)
               .build();
       inscriptosRepository.save(inscripcion);

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
        if(inscriptosRepository.existsByUsuario_IdAndClase_Id(idUser, idClase)){
            inscriptosRepository.removeInscriptosByUsuario_IdAndClase_Id(idUser, idClase);
        }
    }

    public List<Inscriptos> mostrarInscripciones(){
        return inscriptosRepository.findAll();
    }
}
