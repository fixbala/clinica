package co.edu.uniquindio.implementaciones;

import co.edu.uniquindio.modelo.Cita;
import co.edu.uniquindio.repositorios.CitaRepository;
import co.edu.uniquindio.servicios.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Optional<Cita> obtenerCitaPorId(String id) {
        return citaRepository.findById(id);
    }

    @Override
    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();
    }

    @Override
    public void eliminarCita(String id) {
        citaRepository.deleteById(id);
    }
}
