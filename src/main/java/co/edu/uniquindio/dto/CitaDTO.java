package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CitaDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String idCita;
    private Date fechaCreacion;
    private Date fechaCita;
    private LocalTime hora;
    private String estado;
    private String motivoConsulta;
    private String cedulaPaciente; 
    private String cedulaMedico; 
    private String idAtencion; 

    public CitaDTO(String idCita, Date fechaCreacion, Date fechaCita, LocalTime hora, String estado, String motivoConsulta, String cedulaPaciente, String cedulaMedico, String idAtencion) {
        this.idCita = idCita;
        this.fechaCreacion = fechaCreacion;
        this.fechaCita = fechaCita;
        this.hora = hora;
        this.estado = estado;
        this.motivoConsulta = motivoConsulta;
        this.cedulaPaciente = cedulaPaciente;
        this.cedulaMedico = cedulaMedico;
        this.idAtencion = idAtencion;
    }
}
