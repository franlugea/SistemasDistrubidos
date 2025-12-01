package SistemasDistribuidos.Repository;

import SistemasDistribuidos.Entity.Dto.InscriptoDto;
import SistemasDistribuidos.Entity.Enums.EstadoInscripcion;
import SistemasDistribuidos.Entity.Inscriptos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptosRepository extends JpaRepository<Inscriptos, Long> {

    Optional<Inscriptos> findByUsuarioIdAndClaseIdAndEstadoInscripcion(
            Long usuarioId, Long claseId, EstadoInscripcion estadoInscripcion);

    List<Inscriptos> findByUsuarioIdAndEstadoInscripcion(Long usuarioId, EstadoInscripcion estado);


    @Query("SELECT new SistemasDistribuidos.Entity.Dto.InscriptoDto(" +
            "u.nombre, u.email, i.fechaInscripcion) " +
            "FROM Inscriptos i JOIN i.usuario u WHERE i.clase.id = :claseId")
    List<InscriptoDto> findByClaseIdWithUsuario(@Param("claseId") Long claseId);

    boolean existsByUsuarioIdAndClaseIdAndEstadoInscripcion(
            Long usuarioId, Long claseId, EstadoInscripcion estadoInscripcion);

    void deleteByClaseId(Long claseId);
}
