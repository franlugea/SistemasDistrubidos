package SistemasDistribuidos.Entity.Dto;

import java.time.LocalDateTime;

public record InscripcionDto(Long id, ClaseDto clase, UsuarioDto usuario, LocalDateTime fechaInscripcion) {
}
