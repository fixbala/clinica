package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class respuestaPqrs implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_respuesta;
    private Date fecha;
    private int num_radicado;




}