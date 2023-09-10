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
public class cita implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int id_cita;
    private Date fecha_creacion;
    private Date fecha_cita;
    private LocalTime hora;
    private int estado;
    private int cedula_paciente;
    private int cedula_medico;




}