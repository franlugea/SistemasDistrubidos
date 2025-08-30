package SistemasDistribuidos.Repository;

import SistemasDistribuidos.Entity.Inscriptos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptosRepository extends JpaRepository<Inscriptos, Long> {
    void removeInscriptosByUsuario_IdAndClase_Id(Long usuarioId, Long claseId);
    boolean existsByUsuario_IdAndClase_Id(Long usuarioId, Long claseId);
}
