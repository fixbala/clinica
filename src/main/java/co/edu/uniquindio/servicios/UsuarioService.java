package co.edu.uniquindio.servicios;

import java.util.List;
import java.util.Optional;

import co.edu.uniquindio.modelo.Usuario;

public interface UsuarioService {
    
    Usuario guardarUsuario(Usuario usuario);
    Optional<Usuario> obtenerUsuarioPorCedula(String cedula);
    List<Usuario> obtenerTodosLosUsuarios();
    void eliminarUsuario(String cedula);

}
