package SistemasDistribuidos.Entidades.Dto;

import SistemasDistribuidos.Entidades.Enums.DiaSemana;
import SistemasDistribuidos.Entidades.Enums.EstadoClaseEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record CrearClaseDto(@Min(value = 1,message = "El cupo maximo debe ser al menos 1") int cupoMaximo, @NotBlank(message = "El nombre del instructor no puede estar vacio") String instructor
        , @NotNull(message = "El día de la semana es obligatorio") DiaSemana diaSemana,@NotNull(message = "El estado de la clase es obligatorio") EstadoClaseEnum estadoClase,
                           @NotNull(message = "La hora de inicio es obligatoria") LocalTime horaInicio,@NotNull(message = "La hora de finalizacion es obligatoria") LocalTime horaFinal,
                            @NotNull(message = "El ID de la actividad física es obligatorio")@Min(value = 1,message = "El ID de la actividad física debe ser válido") Long actividadFisicaId) {
}
