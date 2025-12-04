package SistemasDistribuidos.Entidades.Dto;

import java.time.LocalDateTime;

public record RespuestaError(String mensaje,
                             String detalle,
                             int estado,
                             LocalDateTime timestamp) {
}
