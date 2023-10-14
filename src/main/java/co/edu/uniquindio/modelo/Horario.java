package co.edu.uniquindio.modelo;

import java.time.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
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

    private DayOfWeek diaSemana; // Puedes usar el tipo enum DayOfWeek para representar los días de la semana
    private LocalTime hora_inicio;
    private LocalTime hora_final;

    public Horario(Medico medico, DayOfWeek diaSemana, LocalTime hora_inicio, LocalTime hora_final) {
        this.medico = medico;
        this.diaSemana = diaSemana;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
    }
}
