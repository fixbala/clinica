package co.edu.uniquindio.implementaciones;

import co.edu.uniquindio.modelo.RespuestaPqrs;
import co.edu.uniquindio.repositorios.RespuestaPqrsRepository;
import co.edu.uniquindio.servicios.RespuestaPqrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespuestaPqrsServiceImpl implements RespuestaPqrsService {

    @Autowired
    private RespuestaPqrsRepository respuestaPqrsRepository;

    @Override
    public RespuestaPqrs guardarRespuestaPqrs(RespuestaPqrs respuestaPqrs) {
        return respuestaPqrsRepository.save(respuestaPqrs);
    }

    @Override
    public RespuestaPqrs actualizarRespuestaPqrs(RespuestaPqrs respuestaPqrs) {
        return respuestaPqrsRepository.save(respuestaPqrs);
    }

    @Override
    public void eliminarRespuestaPqrs(String idRespuesta) {
        respuestaPqrsRepository.deleteById(idRespuesta);
    }

    @Override
    public RespuestaPqrs obtenerRespuestaPqrsPorId(String idRespuesta) {
        Optional<RespuestaPqrs> respuestaPqrsOptional = respuestaPqrsRepository.findById(idRespuesta);
        return respuestaPqrsOptional.orElse(null);
    }
}
