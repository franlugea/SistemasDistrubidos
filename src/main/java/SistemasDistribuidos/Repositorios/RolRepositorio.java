package SistemasDistribuidos.Repositorios;

import SistemasDistribuidos.Entidades.Enums.RolEnum;
import SistemasDistribuidos.Entidades.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {

    Rol findByNombre(RolEnum nombre);

}
