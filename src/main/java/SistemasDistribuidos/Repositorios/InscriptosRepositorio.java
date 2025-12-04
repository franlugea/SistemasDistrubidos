package SistemasDistribuidos.Repositorios;

import SistemasDistribuidos.Entidades.Dto.InscriptoDto;
import SistemasDistribuidos.Entidades.Enums.EstadoInscripcion;
import SistemasDistribuidos.Entidades.Inscriptos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptosRepositorio extends JpaRepository<Inscriptos, Long> {

    Optional<Inscriptos> findByUsuarioIdAndClaseIdAndEstadoInscripcion(
            Long usuarioId, Long claseId, EstadoInscripcion estadoInscripcion);

    List<Inscriptos> findByUsuarioIdAndEstadoInscripcion(Long usuarioId, EstadoInscripcion estado);


    @Query("SELECT new SistemasDistribuidos.Entidades.Dto.InscriptoDto(" +
            "u.nombre, u.email, i.fechaInscripcion) " +
            "FROM Inscriptos i JOIN i.usuario u WHERE i.clase.id = :claseId AND i.estadoInscripcion='ACTIVA'")
    List<InscriptoDto> findByClaseIdWithUsuario(@Param("claseId") Long claseId);

    boolean existsByUsuarioIdAndClaseIdAndEstadoInscripcion(
            Long usuarioId, Long claseId, EstadoInscripcion estadoInscripcion);

    void deleteByClaseId(Long claseId);
}
