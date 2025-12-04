package SistemasDistribuidos.Repositorios;

import SistemasDistribuidos.Entidades.Clase;
import SistemasDistribuidos.Entidades.Enums.EstadoClaseEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClaseRepositorio extends JpaRepository<Clase, Long> {
    Optional<Clase> findById(Long Id);

    List<Clase> findByEstadoClaseIn(Collection<EstadoClaseEnum> estadoClases);
}
