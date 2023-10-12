package co.edu.uniquindio.implementaciones;

import co.edu.uniquindio.modelo.Medico;
import co.edu.uniquindio.repositorios.MedicoRepository;
import co.edu.uniquindio.servicios.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;


    @Override
    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public Optional<Medico> obtenerMedicoPorCodigo(String codigo) {
        return medicoRepository.findById(codigo);
    }

    @Override
    public List<Medico> obtenerTodosLosMedicos() {
        return medicoRepository.findAll();
    }

    @Override
    public void eliminarMedico(String codigo) {
        medicoRepository.deleteById(codigo);
    }
}
