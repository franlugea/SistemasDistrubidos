package SistemasDistribuidos.Entity.Dto;

import java.time.LocalDateTime;

public record InscripcionDto(Long id, ClaseDto claseDto, UsuarioDto usuarioDto, LocalDateTime fechaInscripcion) {
}
