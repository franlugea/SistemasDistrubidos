package SistemasDistribuidos.Service;

import SistemasDistribuidos.Entity.ActividadFisica;
import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.ClaseDto;
import SistemasDistribuidos.Entity.Dto.CrearClaseDto;
import SistemasDistribuidos.Entity.Dto.EditarClaseDto;
import SistemasDistribuidos.Entity.Enums.DiaSemana;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;
import SistemasDistribuidos.Mapper.ClaseMapper;
import SistemasDistribuidos.Repository.ActividadFisicaRepository;
import SistemasDistribuidos.Repository.ClaseRepository;
import SistemasDistribuidos.Repository.InscriptosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private ActividadFisicaRepository actividadFisicaRepository;

    @Autowired
    private ClaseMapper claseMapper;
    @Autowired
    private InscriptosRepository inscriptosRepository;



    public List<ClaseDto> obtenerTodasLasClases(Authentication auth){
        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
        if(esAdmin){
            List<Clase> clases = claseRepository.findAll();
            return claseMapper.toClaseDtoList(clases);
        }
        List<Clase> clases = claseRepository.findByEstadoClaseIn(List.of(EstadoClaseEnum.ACTIVA,EstadoClaseEnum.CANCELADA));
        return claseMapper.toClaseDtoList(clases);
    }

    @Transactional
    public ClaseDto crearClase(CrearClaseDto claseDto){
        ActividadFisica actividadFisica = actividadFisicaRepository.findById(claseDto.actividadFisicaId())
                .orElseThrow(()-> new RuntimeException("Actividad Fisica no encontrada"));

        Clase clase= claseMapper.toEntity(claseDto);
        clase.setActividadFisica(actividadFisica);

        clase= claseRepository.save(clase);
        return claseMapper.toClaseDto(clase);
    }

    @Transactional
    public List<ClaseDto> editarClase(Long id,EditarClaseDto dto){
        Clase clase=claseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Clase no encontrada"));

        if (dto.cupoMaximo()!= 0) {
            clase.setCupo_disponible(dto.cupoMaximo() - (clase.getCupo_maximo() - clase.getCupo_disponible()));
            System.out.println("SEXOOOO");
        }

        claseMapper.editarClase(dto, clase);



        claseRepository.save(clase);
        List<Clase> clases = claseRepository.findAll();
        return claseMapper.toClaseDtoList(clases);
    }

    @Transactional
    public void eliminarClase(Long id){
        Clase clase=claseRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Clase no encontrada"));

        inscriptosRepository.deleteByClaseId(id);
        claseRepository.delete(clase);
    }


}
