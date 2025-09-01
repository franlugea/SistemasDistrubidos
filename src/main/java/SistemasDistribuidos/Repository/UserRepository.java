package SistemasDistribuidos.Repository;

import SistemasDistribuidos.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    Usuario findByAuth0Id(String auth0Id);

    boolean existsByAuth0Id(String auth0Id);
}
