package co.edu.uniquindio.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id_atencion;

    @OneToOne
    @JoinColumn(name = "num_radicado", referencedColumnName = "num_radicado")
    private Pqrs pqrs; // Relación @OneToOne con la tabla Pqrs

    @OneToOne
    @JoinColumn(name = "id_cita", referencedColumnName = "id_cita")
    private Cita cita; // Relación @OneToOne con la tabla cita

    @Column(length = 255) // Ajusta la longitud según tus necesidades
    private String sintomas;

    @Column(length = 255) // Ajusta la longitud según tus necesidades
    private String diagnostico;

    @Column(length = 255) // Ajusta la longitud según tus necesidades
    private String tratamiento;

    public Atencion(String id_atencion, Pqrs pqrs, Cita cita, String sintomas, String diagnostico, String tratamiento) {
        this.id_atencion = id_atencion;
        this.pqrs = pqrs;
        this.cita = cita;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }
}
