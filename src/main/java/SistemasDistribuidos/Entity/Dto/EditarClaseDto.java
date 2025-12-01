package SistemasDistribuidos.Entity.Dto;

import SistemasDistribuidos.Entity.Enums.DiaSemana;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;

import java.time.LocalTime;

public record EditarClaseDto(int cupoMaximo, String instructor, DiaSemana diaSemana, LocalTime horaInicio,
                             LocalTime horaFinal, EstadoClaseEnum estadoClase) {
}
