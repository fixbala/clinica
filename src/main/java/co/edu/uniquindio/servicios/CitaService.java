package co.edu.uniquindio.servicios;

import co.edu.uniquindio.modelo.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    Cita guardarCita(Cita cita);
    Optional<Cita> obtenerCitaPorId(String id);
    List<Cita> obtenerTodasLasCitas();
    void eliminarCita(String id);
}
