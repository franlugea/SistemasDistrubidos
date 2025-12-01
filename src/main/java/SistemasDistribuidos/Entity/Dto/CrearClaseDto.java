package SistemasDistribuidos.Entity.Dto;

import SistemasDistribuidos.Entity.Enums.DiaSemana;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;

import java.time.LocalTime;

public record CrearClaseDto(int cupoMaximo, String instructor, DiaSemana diaSemana, EstadoClaseEnum estadoClase,
                            LocalTime horaInicio, LocalTime horaFinal, Long actividadFisicaId) {
}
