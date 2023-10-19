package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PqrsDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String numRadicado;
    private Date fechaCreacion;
    private int estado;
    private String detalle;
    private String cedulaPaciente; 
    private List<RespuestaPqrsDTO> respuestas;
    private String idAtencion; 

    public PqrsDTO(String numRadicado, Date fechaCreacion, int estado, String detalle, String cedulaPaciente, List<RespuestaPqrsDTO> respuestas, String idAtencion) {
        this.numRadicado = numRadicado;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.detalle = detalle;
        this.cedulaPaciente = cedulaPaciente;
        this.respuestas = respuestas;
        this.idAtencion = idAtencion;
    }
}
