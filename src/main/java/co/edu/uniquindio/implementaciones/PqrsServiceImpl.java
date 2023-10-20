package co.edu.uniquindio.implementaciones;
import co.edu.uniquindio.modelo.Pqrs;
import co.edu.uniquindio.repositorios.PqrsRepository;
import co.edu.uniquindio.servicios.PqrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PqrsServiceImpl implements PqrsService {

    private final PqrsRepository pqrsRepository;

    public PqrsServiceImpl(PqrsRepository pqrsRepository) {
        this.pqrsRepository = pqrsRepository;
    }

    @Override
    public Pqrs guardarPqrs(Pqrs pqrs) {
        return pqrsRepository.save(pqrs);
    }

    @Override
    public Pqrs actualizarPqrs(Pqrs pqrs) {
        return pqrsRepository.save(pqrs);
    }

    @Override
    public void eliminarPqrs(String numRadicado) {
        pqrsRepository.deleteById(numRadicado);
    }

    @Override
    public Pqrs obtenerPqrsPorNumeroRadicado(String numRadicado) {
        Optional<Pqrs> pqrs = pqrsRepository.findById(numRadicado);
        return pqrs.orElse(null);
    }
}
