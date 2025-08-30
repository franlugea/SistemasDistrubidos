package SistemasDistribuidos.Repository;

import SistemasDistribuidos.Entity.Clase;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {
    Optional<Clase> findById(Long Id);

    List<Clase> findAllByEstadoClase(EstadoClaseEnum estadoClase);
}
