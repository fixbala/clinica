package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class usuario implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private int cedula;
    private String password;
    private String nombre;
    private String email;
    @ElementCollection
    private List<String> telefono;
    private String ciudad;
    private String foto;


}