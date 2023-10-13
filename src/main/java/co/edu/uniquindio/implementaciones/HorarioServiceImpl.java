package co.edu.uniquindio.implementaciones;

import co.edu.uniquindio.modelo.Horario;
import co.edu.uniquindio.repositorios.HorarioRepository;
import co.edu.uniquindio.servicios.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public Horario guardarHorario(Horario horario) {
        return horarioRepository.save(horario);
    }

    @Override
    public Optional<Horario> obtenerHorarioPorId(Long id) {
        return horarioRepository.findById(id);
    }

    @Override
    public List<Horario> obtenerTodosLosHorarios() {
        return horarioRepository.findAll();
    }

    @Override
    public void eliminarHorario(Long id) {
        horarioRepository.deleteById(id);
    }
}
