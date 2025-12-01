package SistemasDistribuidos.Entity.Dto;


import java.time.LocalDateTime;

public record InscriptoDto(String nombre, String email, LocalDateTime fechaInscripcion) {
}
