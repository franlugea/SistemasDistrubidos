package SistemasDistribuidos.Entidades;

import SistemasDistribuidos.Entidades.Enums.EstadoInscripcion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscriptos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clase_id", nullable = false)
    private Clase clase;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoInscripcion estadoInscripcion;

    @CreationTimestamp
    @Column(name = "fecha_inscripcion",updatable = false)
    private LocalDateTime fechaInscripcion;
}
