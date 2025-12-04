package SistemasDistribuidos.Entidades.Dto;

import java.time.LocalTime;

public record ClaseDto(Long id, int cupoMaximo, int cupoDisponible,String instructor, String diaSemana, LocalTime horaInicio, LocalTime horaFinal
        , String estadoClase, ActividadFisicaDto actividadFisica) {
}
