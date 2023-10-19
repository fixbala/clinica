package co.edu.uniquindio.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AtencionDTO implements Serializable {

    @EqualsAndHashCode.Include
    private String idAtencion;
    private String numRadicadoPqrs; 
    private String idCita; 
    private String sintomas;
    private String diagnostico;
    private String tratamiento;

    public AtencionDTO(String idAtencion, String numRadicadoPqrs, String idCita, String sintomas, String diagnostico, String tratamiento) {
        this.idAtencion = idAtencion;
        this.numRadicadoPqrs = numRadicadoPqrs;
        this.idCita = idCita;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }
}
