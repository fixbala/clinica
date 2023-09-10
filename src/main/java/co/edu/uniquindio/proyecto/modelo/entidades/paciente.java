package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class paciente implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int codigo;
    private Date fecha;
    private String eps;
    private String tipo_sangre;



}