package co.edu.uniquindio.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo; // Cambio de tipo int a String (VARCHAR)
    private String especialidad; // Nuevo campo "especialidad"
    private LocalTime hora_inicio;
    private LocalTime hora_final;

    @OneToOne
    @JoinColumn(name = "cedula_usuario", referencedColumnName = "cedula")

    private Usuario usuario; // Relación @OneToOne con la tabla usuario

    @OneToMany(mappedBy = "medico")
    private List<Cita> Citas; // Relación @OneToMany con la tabla cita

    public Medico(String codigo, String especialidad, LocalTime hora_inicio, LocalTime hora_final, Usuario usuario) {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.usuario = usuario;
    }
}
