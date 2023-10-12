package co.edu.uniquindio.implementaciones;
import co.edu.uniquindio.modelo.Paciente;
import co.edu.uniquindio.repositorios.PacienteRepository;
import co.edu.uniquindio.servicios.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public void eliminarPaciente(String codigo) {
        pacienteRepository.deleteById(codigo);
    }

    @Override
    public Paciente obtenerPacientePorCodigo(String codigo) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(codigo);
        return pacienteOptional.orElse(null);
    }


}
