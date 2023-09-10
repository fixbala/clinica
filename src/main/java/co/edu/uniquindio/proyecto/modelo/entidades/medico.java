package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class medico implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int codigo;
    private LocalTime hora_inicio;
    private LocalTime hora_final;
    private int cedula_usuario;




}