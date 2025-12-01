package SistemasDistribuidos.Entity.Dto;

import SistemasDistribuidos.Entity.ActividadFisica;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;

import java.time.LocalTime;

public record ClaseDto(Long id, int cupo_maximo, int cupo_disponible,String instructor, String diaSemana, LocalTime horaInicio, LocalTime horaFinal
        , String estadoClase, ActividadFisicaDto actividadFisica) {
}
