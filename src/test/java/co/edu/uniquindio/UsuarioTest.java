package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Test
    public void registrarUsuarioTest() {
        // Crear un usuario para la prueba
        Usuario u = new Usuario();
        u.setCedula("111");
        u.setNombre("Juanita");
        u.setEmail("juana@mail.com");

        // Guardamos el registro
        Usuario guardado = usuarioRepo.save(u);

        // Comprobamos que sí haya quedado
        assertNotNull(guardado);
    }

    @Test
    public void buscarUsuarioPorIdTest() {
        // Crear un usuario para la prueba
        Usuario u = new Usuario();
        u.setCedula("222");
        u.setNombre("Pedro");
        u.setEmail("pedro@mail.com");
        usuarioRepo.save(u);

        // Buscar el usuario por ID
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findById(u.getCedula());

        // Comprobamos que el usuario haya sido encontrado
        assertTrue(usuarioEncontrado.isPresent());
    }

    @Test
    public void actualizarUsuarioTest() {
        // Crear un usuario para la prueba
        Usuario u = new Usuario();
        u.setCedula("333");
        u.setNombre("Maria");
        u.setEmail("maria@mail.com");
        usuarioRepo.save(u);

        // Modificar el nombre del usuario
        u.setNombre("Maria Updated");
        usuarioRepo.save(u);

        // Buscar el usuario por ID después de la actualización
        Optional<Usuario> usuarioActualizado = usuarioRepo.findById(u.getCedula());

        // Comprobamos que el nombre haya sido actualizado
        assertTrue(usuarioActualizado.isPresent());
        assertEquals("Maria Updated", usuarioActualizado.get().getNombre());
    }

    @Test
    public void eliminarUsuarioTest() {
        // Crear un usuario para la prueba
        Usuario u = new Usuario();
        u.setCedula("444");
        u.setNombre("Carlos");
        u.setEmail("carlos@mail.com");
        usuarioRepo.save(u);

        // Eliminar el usuario por ID
        usuarioRepo.deleteById(u.getCedula());

        // Intentar buscar el usuario después de eliminarlo
        Optional<Usuario> usuarioEliminado = usuarioRepo.findById(u.getCedula());

        // Comprobamos que el usuario haya sido eliminado
        assertTrue(usuarioEliminado.isEmpty());
    }

    @Test
    public void listarUsuariosTest() {
        // Crear varios usuarios para la prueba
        Usuario u1 = new Usuario();
        u1.setCedula("555");
        u1.setNombre("Laura");
        u1.setEmail("laura@mail.com");
        usuarioRepo.save(u1);

        Usuario u2 = new Usuario();
        u2.setCedula("666");
        u2.setNombre("Juan");
        u2.setEmail("juan@mail.com");
        usuarioRepo.save(u2);

        // Obtener la lista de todos los usuarios
        List<Usuario> usuarios = usuarioRepo.findAll();

        // Comprobamos que hay al menos dos usuarios en la lista
        assertTrue(usuarios.size() >= 2);
    }
}
