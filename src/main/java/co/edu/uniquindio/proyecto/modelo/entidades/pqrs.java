package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class pqrs implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int num_radicado;
    private Date fecha_creacion;
    private int estado;
    private int id_cita;
    private int cedula_paciente;




}