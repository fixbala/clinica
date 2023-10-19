package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UsuarioDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String cedula;
    private String nombre;
    private String email;
    private List<String> telefono;
    private String ciudad;
    private String foto;

    public UsuarioDTO(String cedula, String nombre, String email, List<String> telefono, String ciudad, String foto) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.foto = foto;
    }
}
