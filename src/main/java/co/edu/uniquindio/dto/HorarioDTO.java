package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HorarioDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;
    private String codigoMedico; 
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFinal;

    public HorarioDTO(Long id, String codigoMedico, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFinal) {
        this.id = id;
        this.codigoMedico = codigoMedico;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }
}
