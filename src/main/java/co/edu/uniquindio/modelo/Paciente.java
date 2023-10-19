package co.edu.uniquindio.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo; 
    private Date fecha;
    private String eps;
    private String tipo_sangre;

    @OneToOne
    @JoinColumn(name = "cedula_usuario", referencedColumnName = "cedula")
    private Usuario usuario; // Relación @OneToOne con la tabla usuario

    @OneToMany(mappedBy = "paciente")
    private List<Cita> Citas; // Relación @OneToMany con la tabla cita

    @OneToMany(mappedBy = "paciente")
    private List<Pqrs> pqrs; // Relación @OneToMany con la tabla pqrs

    public Paciente(String codigo, Date fecha, String eps, String tipo_sangre, Usuario usuario) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.eps = eps;
        this.tipo_sangre = tipo_sangre;
        this.usuario = usuario;
    }
}
