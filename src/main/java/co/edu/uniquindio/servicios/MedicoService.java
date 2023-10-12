package co.edu.uniquindio.servicios;

import co.edu.uniquindio.modelo.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoService {

    Medico guardarMedico(Medico medico);
    Optional<Medico> obtenerMedicoPorCodigo(String codigo);
    List<Medico> obtenerTodosLosMedicos();
    void eliminarMedico(String codigo);
}
