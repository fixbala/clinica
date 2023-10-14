package co.edu.uniquindio.implementaciones;

import co.edu.uniquindio.modelo.Atencion;
import co.edu.uniquindio.repositorios.AtencionRepository;
import co.edu.uniquindio.servicios.AtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private AtencionRepository atencionRepository;

    @Override
    public Atencion guardarAtencion(Atencion atencion) {
        return atencionRepository.save(atencion);
    }

    @Override
    public Optional<Atencion> obtenerAtencionPorId(String id) {
        return atencionRepository.findById(id);
    }

    @Override
    public List<Atencion> obtenerTodasLasAtenciones() {
        return atencionRepository.findAll();
    }

    @Override
    public void eliminarAtencion(String id) {
        atencionRepository.deleteById(id);
    }
}
