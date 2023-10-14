package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Administrador;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.AdministradorRepository;
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
public class AdministradorTest {

    @Autowired
    private AdministradorRepository administradorRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Test
    public void registrarAdministradorTest() {
        // Crear un usuario para el administrador
        Usuario usuario = new Usuario();
        usuario.setCedula("111");
        usuario.setNombre("Juanita");
        usuario.setEmail("juana@mail.com");
        usuarioRepo.save(usuario);

        // Crear un administrador para la prueba
        Administrador administrador = new Administrador();
        administrador.setCodigo("A001");
        administrador.setUsuario(usuario);

        // Guardar el registro
        Administrador guardado = administradorRepo.save(administrador);

        // Comprobamos que sí haya quedado
        assertNotNull(guardado);
}

    @Test
    public void buscarAdministradorPorIdTest() {
        // Crear un usuario para el administrador
        Usuario usuario = new Usuario();
        usuario.setCedula("222");
        usuario.setNombre("Pedro");
        usuario.setEmail("pedro@mail.com");
        usuarioRepo.save(usuario);

        // Crear un administrador para la prueba
        Administrador administrador = new Administrador();
        administrador.setCodigo("A002");
        administrador.setUsuario(usuario);
        administradorRepo.save(administrador);

        // Buscar el administrador por ID
        Optional<Administrador> administradorEncontrado = administradorRepo.findById(administrador.getCodigo());

        // Comprobamos que el administrador haya sido encontrado
        assertTrue(administradorEncontrado.isPresent());
    }

    @Test
    public void actualizarAdministradorTest() {
        // Crear un usuario para el administrador
        Usuario usuario = new Usuario();
        usuario.setCedula("333");
        usuario.setNombre("Maria");
        usuario.setEmail("maria@mail.com");
        usuarioRepo.save(usuario);

        // Crear un administrador para la prueba
        Administrador administrador = new Administrador();
        administrador.setCodigo("A003");
        administrador.setUsuario(usuario);
        administradorRepo.save(administrador);

        // Modificar el código del administrador
        administrador.setCodigo("A003-Updated");
        administradorRepo.save(administrador);

        // Buscar el administrador por ID después de la actualización
        Optional<Administrador> administradorActualizado = administradorRepo.findById(administrador.getCodigo());

        // Comprobamos que el código haya sido actualizado
        assertTrue(administradorActualizado.isPresent());
        assertEquals("A003-Updated", administradorActualizado.get().getCodigo());
    }

    @Test
    public void eliminarAdministradorTest() {
        // Crear un usuario para el administrador
        Usuario usuario = new Usuario();
        usuario.setCedula("444");
        usuario.setNombre("Carlos");
        usuario.setEmail("carlos@mail.com");
        usuarioRepo.save(usuario);

        // Crear un administrador para la prueba
        Administrador administrador = new Administrador();
        administrador.setCodigo("A004");
        administrador.setUsuario(usuario);
        administradorRepo.save(administrador);

        // Eliminar el administrador por ID
        administradorRepo.deleteById(administrador.getCodigo());

        // Intentar buscar el administrador después de eliminarlo
        Optional<Administrador> administradorEliminado = administradorRepo.findById(administrador.getCodigo());

        // Comprobamos que el administrador haya sido eliminado
        assertTrue(administradorEliminado.isEmpty());
    }

    @Test
    public void listarAdministradoresTest() {
        // Crear varios usuarios para el administrador
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

        // Crear varios administradores para la prueba
        Administrador a1 = new Administrador();
        a1.setCodigo("A005");
        a1.setUsuario(u1);
        administradorRepo.save(a1);

        Administrador a2 = new Administrador();
        a2.setCodigo("A006");
        a2.setUsuario(u2);
        administradorRepo.save(a2);

        // Obtener la lista de todos los administradores
        List<Administrador> administradores = administradorRepo.findAll();

        // Comprobamos que hay al menos dos administradores en la lista
        assertTrue(administradores.size() >= 2);
    }
}
