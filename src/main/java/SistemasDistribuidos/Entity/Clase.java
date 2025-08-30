package SistemasDistribuidos.Entity;

import SistemasDistribuidos.Entity.Enums.DiaSemana;
import SistemasDistribuidos.Entity.Enums.EstadoClaseEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cupo_maximo;

    private int cupo_disponible;

    private String instructor;

    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    private LocalTime horaInicio;

    private LocalTime horaFinal;

    @Enumerated(EnumType.STRING)
    private EstadoClaseEnum estadoClase;

    @ManyToOne
    @JoinColumn(name = "actividad_id",nullable = false)
    private ActividadFisica actividadFisica;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Inscriptos> inscripciones;
}
