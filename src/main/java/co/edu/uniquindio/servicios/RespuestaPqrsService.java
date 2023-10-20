package co.edu.uniquindio.servicios;

import co.edu.uniquindio.modelo.RespuestaPqrs;

public interface RespuestaPqrsService {
    RespuestaPqrs guardarRespuestaPqrs(RespuestaPqrs respuestaPqrs);
    RespuestaPqrs actualizarRespuestaPqrs(RespuestaPqrs respuestaPqrs);
    void eliminarRespuestaPqrs(String idRespuesta);
    RespuestaPqrs obtenerRespuestaPqrsPorId(String idRespuesta);
}
