package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AdministradorDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String codigo;
    private String cedulaUsuario; 

    public AdministradorDTO(String codigo, String cedulaUsuario) {
        this.codigo = codigo;
        this.cedulaUsuario = cedulaUsuario;
    }
}
