package SistemasDistribuidos.Entidades;

import SistemasDistribuidos.Entidades.Enums.RolEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RolEnum nombre;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios;
}
