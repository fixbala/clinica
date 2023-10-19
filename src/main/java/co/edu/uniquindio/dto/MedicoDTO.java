package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicoDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String codigo;
    private String especialidad;
    private String cedulaUsuario; 
    private List<CitaDTO> citas;

    public MedicoDTO(String codigo, String especialidad, LocalTime horaInicio, LocalTime horaFinal, String cedulaUsuario, List<CitaDTO> citas) {
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.cedulaUsuario = cedulaUsuario;
        this.citas = citas;
    }
}
