package co.edu.uniquindio.servicios;

import co.edu.uniquindio.modelo.Pqrs;

public interface PqrsService {
    Pqrs guardarPqrs(Pqrs pqrs);
    Pqrs actualizarPqrs(Pqrs pqrs);
    void eliminarPqrs(String numRadicado);
    Pqrs obtenerPqrsPorNumeroRadicado(String numRadicado);
}
