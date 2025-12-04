package SistemasDistribuidos.Repositorios;

import SistemasDistribuidos.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Usuario findByAuth0Id(String auth0Id);

    boolean existsByAuth0Id(String auth0Id);
}
