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
    private String codigo; 
    private String especialidad; 
    private LocalTime hora_inicio;
    private LocalTime hora_final;

    @OneToOne
    @JoinColumn(name = "cedula_usuario", referencedColumnName = "cedula")
    private Usuario usuario; 

    @OneToMany(mappedBy = "medico")
    private List<Cita> Citas; 

    public Medico(String codigo, String especialidad, LocalTime hora_inicio, LocalTime hora_final, Usuario usuario) {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.usuario = usuario;
    }
}
