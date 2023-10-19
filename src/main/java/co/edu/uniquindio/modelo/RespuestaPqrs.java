package co.edu.uniquindio.modelo;

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

    @Column(length = 1000)
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
