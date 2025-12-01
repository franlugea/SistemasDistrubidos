package SistemasDistribuidos.Entity.Dto;

import java.util.List;
import java.util.Set;

public record CrearUsuarioDto(String auth0Id, String nombre, String email, List<String> roles) {

}
