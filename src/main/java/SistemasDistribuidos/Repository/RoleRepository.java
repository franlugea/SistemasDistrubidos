package SistemasDistribuidos.Repository;

import SistemasDistribuidos.Entity.Enums.RoleEnum;
import SistemasDistribuidos.Entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long> {
    Rol findFirstByNombre(RoleEnum nombre);

}
