package SistemasDistribuidos.Entidades.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CrearUsuarioDto(@NotBlank(message = "El Auth0 ID es obligatorio") String auth0Id,@NotBlank(message = "El nombre es obligatorio") String nombre,@NotBlank(message = "El email es obligatorio") String email,@NotNull(message = "La lista de roles no puede ser nula")
@NotEmpty(message = "El usuario debe tener al menos un rol asignado") List<String> roles) {

}
