package co.edu.uniquindio.implementaciones;

import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import co.edu.uniquindio.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorCedula(String cedula) {
        return usuarioRepository.findById(cedula);
    }

    @Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void eliminarUsuario(String cedula) {
        usuarioRepository.deleteById(cedula);
    }

}
