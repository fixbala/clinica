<<<<<<< HEAD:src/main/java/co/edu/uniquindio/modelo/Pqrs.java
package co.edu.uniquindio.modelo;
=======
package co.edu.uniquindio.proyecto.modelo.entidades;
>>>>>>> d384ac5 (Entities added):src/main/java/co/edu/uniquindio/proyecto/modelo/entidades/Pqrs.java

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
public class Pqrs implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String num_radicado;
    private Date fecha_creacion;
    private int estado;
    private String detalle; // Nuevo atributo de tipo texto

    @ManyToOne
    @JoinColumn(name = "cedula_paciente")
    private Paciente paciente; // Relación @ManyToOne con la tabla paciente

    @OneToMany(mappedBy = "pqrs")
    private List<RespuestaPqrs> respuestas; // Relación @OneToMany con la tabla respuestaPqrs

    @OneToOne
    @JoinColumn(name = "id_atencion") // Cambia el nombre del campo a la clave primaria de la tabla Atencion
    private Atencion atencion; // Relación @OneToOne con la tabla Atencion

    public Pqrs(String num_radicado, Date fecha_creacion, int estado, Paciente paciente, String detalle, Atencion atencion) {
        this.num_radicado = num_radicado;
        this.fecha_creacion = fecha_creacion;
        this.estado = estado;
        this.paciente = paciente;
        this.detalle = detalle;
        this.atencion = atencion;
    }
}
