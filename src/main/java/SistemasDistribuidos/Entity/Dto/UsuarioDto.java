package SistemasDistribuidos.Entity.Dto;

import java.util.Set;

public record UsuarioDto(Long id, String nombre, Set<RolDto> roles) {
}
