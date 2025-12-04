package SistemasDistribuidos.Servicios.Impl;

import SistemasDistribuidos.Entidades.ActividadFisica;
import SistemasDistribuidos.Entidades.Clase;
import SistemasDistribuidos.Entidades.Dto.ClaseDto;
import SistemasDistribuidos.Entidades.Dto.CrearClaseDto;
import SistemasDistribuidos.Entidades.Dto.EditarClaseDto;
import SistemasDistribuidos.Entidades.Enums.EstadoClaseEnum;
import SistemasDistribuidos.Exepciones.RecursoNoEncontradoExcepcion;
import SistemasDistribuidos.Mapper.ClaseMapper;
import SistemasDistribuidos.Repositorios.ActividadFisicaRepositorio;
import SistemasDistribuidos.Repositorios.ClaseRepositorio;
import SistemasDistribuidos.Repositorios.InscriptosRepositorio;
import SistemasDistribuidos.Servicios.ClaseServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClaseServicioImpl implements ClaseServicio {

    @Autowired
    private ClaseRepositorio claseRepositorio;

    @Autowired
    private ActividadFisicaRepositorio actividadFisicaRepositorio;

    @Autowired
    private ClaseMapper claseMapper;
    @Autowired
    private InscriptosRepositorio inscriptosRepositorio;


    @Override
    public List<ClaseDto> obtenerTodasLasClases(){
        List<Clase> clases = claseRepositorio.findAll();
        List<ClaseDto> clasesDto= claseMapper.toClaseDtoList(clases);
        System.out.println("Cupo maximo:"+ clasesDto.get(0).cupoMaximo()+ "Cupo Disponible: "+ clasesDto.get(0).cupoDisponible());
        return clasesDto;
    }

    @Override
    public List<ClaseDto> obtenerClasesDisponibles(){
        List<Clase> clases= claseRepositorio.findByEstadoClaseIn(List.of(EstadoClaseEnum.ACTIVA,EstadoClaseEnum.CANCELADA));
        return claseMapper.toClaseDtoList(clases);
    }

    @Transactional
    @Override
    public ClaseDto crearClase(CrearClaseDto claseDto){
        ActividadFisica actividadFisica = actividadFisicaRepositorio.findById(claseDto.actividadFisicaId())
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro la actividad fisica con ID: " + claseDto.actividadFisicaId()));

        Clase clase= claseMapper.toEntity(claseDto);
        clase.setActividadFisica(actividadFisica);

        clase= claseRepositorio.save(clase);
        return claseMapper.toClaseDto(clase);
    }

    @Transactional
    @Override
    public List<ClaseDto> editarClase(Long id,EditarClaseDto dto){
        Clase clase= claseRepositorio.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro la clase con ID: " + id));

        if(dto.cupoMaximo()<(clase.getCupoMaximo()-clase.getCupoDisponible())){
            throw new RuntimeException("El cupo maximo no puede ser menor a la cantidad de usuarios inscriptos");
        }

        if (dto.cupoMaximo()!= 0) {
            clase.setCupoDisponible(dto.cupoMaximo() - (clase.getCupoMaximo() - clase.getCupoDisponible()));
        }

        claseMapper.editarClase(dto, clase);


        claseRepositorio.save(clase);
        List<Clase> clases = claseRepositorio.findAll();
        return claseMapper.toClaseDtoList(clases);
    }

    @Transactional
    @Override
    public void eliminarClase(Long id){
        Clase clase= claseRepositorio.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoExcepcion("No se encontro la clase con ID: " + id));

        inscriptosRepositorio.deleteByClaseId(id);
        claseRepositorio.delete(clase);
    }


}
