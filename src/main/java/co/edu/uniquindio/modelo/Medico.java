package co.edu.uniquindio.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
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

    @OneToOne
    @JoinColumn(name = "cedula_usuario", referencedColumnName = "cedula")
    private Usuario usuario;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Horario> horarios = new ArrayList<>();  

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

    public Medico(String codigo, String especialidad, Usuario usuario, List<Horario> horarios) {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.usuario = usuario;
        this.horarios = horarios;
    }
}
