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
public class atencion implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_atencion;

    private int id_cita;
    private int num_radicado;





}