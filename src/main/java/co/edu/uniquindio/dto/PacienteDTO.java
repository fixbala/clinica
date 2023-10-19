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
public class PacienteDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String codigo;
    private Date fecha;
    private String eps;
    private String tipoSangre;
    private String cedulaUsuario; 
    private List<CitaDTO> citas;
    private List<PqrsDTO> pqrs;

    public PacienteDTO(String codigo, Date fecha, String eps, String tipoSangre, String cedulaUsuario, List<CitaDTO> citas, List<PqrsDTO> pqrs) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.eps = eps;
        this.tipoSangre = tipoSangre;
        this.cedulaUsuario = cedulaUsuario;
        this.citas = citas;
        this.pqrs = pqrs;
    }
}
