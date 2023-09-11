package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
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
public class Cita implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id_cita;
    private Date fecha_creacion;
    private Date fecha_cita;
    private LocalTime hora;
    private String estado;

    // Nuevo campo motivo_consulta
    @Column(length = 255) // Ajusta la longitud según tus necesidades
    private String motivo_consulta;

    @OneToOne(mappedBy = "cita")
    private Atencion atencion; // Relación @OneToOne con la tabla Atencion

    @ManyToOne
    @JoinColumn(name = "cedula_paciente")
    private Paciente paciente; // Relación @ManyToOne con la tabla paciente

    @ManyToOne
    @JoinColumn(name = "cedula_medico")
    private Medico medico; // Relación @ManyToOne con la tabla medico

    public Cita(String id_cita, Date fecha_creacion, Date fecha_cita, LocalTime hora, String estado, Paciente paciente, Medico medico, Atencion atencion, String motivo_consulta) {
        this.id_cita = id_cita;
        this.fecha_creacion = fecha_creacion;
        this.fecha_cita = fecha_cita;
        this.hora = hora;
        this.estado = estado;
        this.paciente = paciente;
        this.medico = medico;
        this.atencion = atencion;
        this.motivo_consulta = motivo_consulta;
    }
}
