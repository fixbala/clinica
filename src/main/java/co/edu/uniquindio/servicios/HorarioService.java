package co.edu.uniquindio.servicios;

import co.edu.uniquindio.modelo.Horario;

import java.util.List;
import java.util.Optional;

public interface HorarioService {

    Horario guardarHorario(Horario horario);
    Optional<Horario> obtenerHorarioPorId(Long id);
    List<Horario> obtenerTodosLosHorarios();
    void eliminarHorario(Long id);
}
