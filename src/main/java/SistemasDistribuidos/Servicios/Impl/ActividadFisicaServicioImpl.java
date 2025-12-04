package SistemasDistribuidos.Servicios.Impl;

import SistemasDistribuidos.Entidades.ActividadFisica;
import SistemasDistribuidos.Entidades.Dto.ActividadFisicaDto;
import SistemasDistribuidos.Mapper.ClaseMapper;
import SistemasDistribuidos.Repositorios.ActividadFisicaRepositorio;
import SistemasDistribuidos.Servicios.ActividadFisicaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadFisicaServicioImpl implements ActividadFisicaServicio {

    @Autowired
    private ActividadFisicaRepositorio actividadFisicaRepositorio;
    @Autowired
    private ClaseMapper claseMapper;

    @Override
    public List<ActividadFisicaDto> obtenerActividades(){
        List<ActividadFisica> actividades= actividadFisicaRepositorio.findAll();
        return claseMapper.toActividadFisicaDtoList(actividades);
    }
}
