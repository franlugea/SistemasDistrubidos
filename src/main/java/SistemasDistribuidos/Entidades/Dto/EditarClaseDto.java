package SistemasDistribuidos.Entidades.Dto;

import SistemasDistribuidos.Entidades.Enums.DiaSemana;
import SistemasDistribuidos.Entidades.Enums.EstadoClaseEnum;
import jakarta.validation.constraints.Min;

import java.time.LocalTime;

public record EditarClaseDto(@Min(value = 1, message = "El cupo máximo debe ser mayor a 0")int cupoMaximo, String instructor, DiaSemana diaSemana, LocalTime horaInicio,
                             LocalTime horaFinal, EstadoClaseEnum estadoClase) {
}
