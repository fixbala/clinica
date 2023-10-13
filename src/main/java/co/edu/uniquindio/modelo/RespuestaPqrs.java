<<<<<<< HEAD:src/main/java/co/edu/uniquindio/modelo/RespuestaPqrs.java
package co.edu.uniquindio.modelo;
=======
package co.edu.uniquindio.proyecto.modelo.entidades;
>>>>>>> d384ac5 (Entities added):src/main/java/co/edu/uniquindio/proyecto/modelo/entidades/RespuestaPqrs.java

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespuestaPqrs implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id_respuesta;
    private Date fecha;

    // Nuevo campo de tipo texto
    @Column(length = 1000) // Ajusta la longitud según tus necesidades
    private String texto;

    @ManyToOne
    @JoinColumn(name = "num_radicado")
    private Pqrs pqrs; // Relación @ManyToOne con la tabla Pqrs

    public RespuestaPqrs(String id_respuesta, Date fecha, String texto, Pqrs pqrs) {
        this.id_respuesta = id_respuesta;
        this.fecha = fecha;
        this.texto = texto;
        this.pqrs = pqrs;
    }
}
