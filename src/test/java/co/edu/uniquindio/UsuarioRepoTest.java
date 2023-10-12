package co.edu.uniquindio.test;

import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UsuarioRepoTest {

    @Autowired
    private UsuarioRepository usuarioRepository;  // Ajusta el nombre del repositorio

    @Test
    public void testGuardarUsuario() {
        // Crear un nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setCedula("123456");
        usuario.setNombre("Nombre de Usuario");

        // Guardar el usuario en la base de datos (Create)
        usuarioRepository.save(usuario);

        // Leer el usuario de la base de datos (Read)
        Optional<Usuario> usuarioGuardadoOptional = usuarioRepository.findById(usuario.getCedula());
        assertTrue(usuarioGuardadoOptional.isPresent());
        Usuario usuarioGuardado = usuarioGuardadoOptional.get();
        boolean a = true;
        assertTrue(true);
    }

    // @Test
    // public void testActualizarUsuario() {
    //     // Crear un nuevo usuario
    //     Usuario usuario = new Usuario();
    //     usuario.setCedula("123456");
    //     usuario.setNombre("Nombre de Usuario");

    //     // Guardar el usuario en la base de datos (Create)
    //     usuarioRepository.save(usuario);

    //     // Actualizar el usuario (Update)
    //     usuario.setNombre("Usuario Modificado");
    //     usuarioRepository.save(usuario);

    //     // Leer el usuario actualizado de la base de datos
    //     Optional<Usuario> usuarioActualizadoOptional = usuarioRepository.findById(usuario.getCedula());
    //     assertTrue(usuarioActualizadoOptional.isPresent());
    //     Usuario usuarioActualizado = usuarioActualizadoOptional.get();
    //     assertEquals("Usuario Modificado", usuarioActualizado.getNombre());
    // }

    // @Test
    // public void testEliminarUsuario() {
    //     // Crear un nuevo usuario
    //     Usuario usuario = new Usuario();
    //     usuario.setCedula("123456");
    //     usuario.setNombre("Nombre de Usuario");

    //     // Guardar el usuario en la base de datos (Create)
    //     usuarioRepository.save(usuario);

    //     // Eliminar el usuario de la base de datos (Delete)
    //     usuarioRepository.deleteById(usuario.getCedula());

    //     // Verificar que el usuario se ha eliminado correctamente
    //     Optional<Usuario> usuarioEliminadoOptional = usuarioRepository.findById(usuario.getCedula());
    //     assertFalse(usuarioEliminadoOptional.isPresent());
    // }
}
