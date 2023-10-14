package co.edu.uniquindio.servicios;

import co.edu.uniquindio.modelo.Atencion;

import java.util.List;
import java.util.Optional;

public interface AtencionService {

    Atencion guardarAtencion(Atencion atencion);

    Optional<Atencion> obtenerAtencionPorId(String id);

    List<Atencion> obtenerTodasLasAtenciones();

    void eliminarAtencion(String id);
}
