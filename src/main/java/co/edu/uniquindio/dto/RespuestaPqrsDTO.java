package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespuestaPqrsDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String idRespuesta;
    private Date fecha;
    private String texto;
    private String numRadicado; 

    public RespuestaPqrsDTO(String idRespuesta, Date fecha, String texto, String numRadicado) {
        this.idRespuesta = idRespuesta;
        this.fecha = fecha;
        this.texto = texto;
        this.numRadicado = numRadicado;
    }
}
