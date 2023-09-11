package co.edu.uniquindio.proyecto.modelo.entidades;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
@Entity
public class administrador implements Serializable {
    @Id
    private int codigo;

    private int cedula;


    public administrador() {
        super();
    }
}