package co.edu.uniquindio.servicios;

import co.edu.uniquindio.modelo.Paciente;

public interface PacienteService {
    Paciente guardarPaciente(Paciente paciente);
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(String codigo);
    Paciente obtenerPacientePorCodigo(String codigo);
}
