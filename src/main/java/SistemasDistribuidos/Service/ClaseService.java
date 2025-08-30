package SistemasDistribuidos.Service;

import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Dto.ClaseDto;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;
import SistemasDistribuidos.Mapper.ClaseMapper;
import SistemasDistribuidos.Repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private ClaseMapper claseMapper;

    public List<ClaseDto> obtenerTodasLasClases(){
        //List<Clase> clases = claseRepository.findAllByEstadoClase(EstadoClaseEnum.ACTIVA);
        List<Clase> clases = claseRepository.findAll();
        return claseMapper.toClaseDtoList(clases);
    }
}
