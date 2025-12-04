package SistemasDistribuidos.Entidades;

import SistemasDistribuidos.Entidades.Enums.DiaSemana;
import SistemasDistribuidos.Entidades.Enums.EstadoClaseEnum;
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

    @Column(nullable = false)
    private int cupoMaximo;

    @Column(nullable = false)
    private int cupoDisponible;

    @Column(nullable = false)
    private String instructor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFinal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoClaseEnum estadoClase;

    @ManyToOne
    @JoinColumn(name = "actividad_id",nullable = false)
    private ActividadFisica actividadFisica;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Inscriptos> inscripciones;
}
