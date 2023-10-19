package co.edu.uniquindio.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Horario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_codigo", referencedColumnName = "codigo")
    private Medico medico;

    private DayOfWeek diaSemana;
    private LocalTime hora_inicio;
    private LocalTime hora_final;

    public Horario(Medico medico, DayOfWeek diaSemana, LocalTime hora_inicio, LocalTime hora_final) {
        this.medico = medico;
        this.diaSemana = diaSemana;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
    }

    @PrePersist
    @PreUpdate
    private void validateHours() {
        if (hora_inicio != null && hora_final != null && hora_inicio.isAfter(hora_final)) {
            throw new IllegalArgumentException("La hora de inicio debe ser antes que la hora final");
        }
    }
}
