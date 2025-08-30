package SistemasDistribuidos.Entity;

import SistemasDistribuidos.Entity.Enums.ActividadFisicaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActividadFisicaEnum nombre;

    @OneToMany(mappedBy = "actividadFisica",cascade = CascadeType.ALL)
    private List<Clase> clases;


}
