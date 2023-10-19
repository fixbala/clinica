package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Paciente;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.PacienteRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;


import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PacienteTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Test
    public void iniciarSesionPacienteExistenteTest() {
        // Crear un usuario para la prueba
        Usuario u = new Usuario();
        u.setCedula("111");
        u.setNombre("Juanita");
        u.setEmail("juana@mail.com");
        u.setPassword("password"); 
        usuarioRepo.save(u);
    
        // Intentar iniciar sesión con el usuario y contraseña correctos
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findByCedulaAndPassword("111", "password");
    
        // Comprobamos que el usuario haya sido encontrado
        assertTrue(usuarioEncontrado.isPresent());
    }

    @Test
    public void iniciarSesionPacienteNoExistenteTest() {
        // Intentar iniciar sesión con un usuario que no existe
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findByCedulaAndPassword("999", "password");

        // Comprobamos que el usuario no haya sido encontrado
        assertTrue(usuarioEncontrado.isEmpty());
    }

    @Test
    public void iniciarSesionContrasenaIncorrectaTest() {
        // Crear un usuario para la prueba
        Usuario u = new Usuario();
        u.setCedula("222");
        u.setNombre("Pedro");
        u.setEmail("pedro@mail.com");
        u.setPassword("password"); // Almacena la contraseña directamente (sin encriptar)
        usuarioRepo.save(u);

        // Intentar iniciar sesión con una contraseña incorrecta
        Optional<Usuario> usuarioEncontrado = usuarioRepo.findByCedulaAndPassword("222", "contrasenaincorrecta");

        // Comprobamos que el usuario no haya sido encontrado
        assertTrue(usuarioEncontrado.isEmpty());
    }

    
    @Test
    public void testGuardarPaciente() {
        Usuario usuario = crearUsuario("123456", "Nombre de Usuario");
        Paciente paciente = crearPaciente("P001", new Date(), "EPS Test", "O+", usuario);
        guardarPaciente(paciente);
        verificarPacienteGuardado(paciente);
        eliminarPacienteYUsuario(paciente, usuario);
    }

    @Test
    public void testActualizarPaciente() {
        Usuario usuario = crearUsuario("123456", "Nombre de Usuario");
        Paciente paciente = crearPaciente("P001", new Date(), "EPS Test", "O+", usuario);
        guardarPaciente(paciente);
        verificarPacienteGuardado(paciente);
        paciente.setEps("EPS Modificada");
        actualizarPaciente(paciente);
        verificarPacienteActualizado(paciente);
        eliminarPacienteYUsuario(paciente, usuario);
    }

    @Test
    public void testEliminarPaciente() {
        Usuario usuario = crearUsuario("123456", "Nombre de Usuario");
        Paciente paciente = crearPaciente("P001", new Date(), "EPS Test", "O+", usuario);
        guardarPaciente(paciente);
        verificarPacienteGuardado(paciente);
        eliminarPaciente(paciente);
        verificarPacienteEliminado(paciente);
        eliminarUsuario(usuario);
    }

    private Usuario crearUsuario(String cedula, String nombre) {
        Usuario usuario = new Usuario();
        usuario.setCedula(cedula);
        usuario.setNombre(nombre);
        usuarioRepository.save(usuario);
        return usuario;
    }

    private Paciente crearPaciente(String codigo, Date fecha, String eps, String tipoSangre, Usuario usuario) {
        Paciente paciente = new Paciente(codigo, fecha, eps, tipoSangre, usuario);
        pacienteRepository.save(paciente);
        return paciente;
    }

    private void guardarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    private void verificarPacienteGuardado(Paciente paciente) {
        Optional<Paciente> pacienteGuardadoOptional = pacienteRepository.findById(paciente.getCodigo());
        assertTrue(pacienteGuardadoOptional.isPresent());
        Paciente pacienteGuardado = pacienteGuardadoOptional.get();
        assertEquals(paciente.getEps(), pacienteGuardado.getEps());
    }

    private void actualizarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    private void verificarPacienteActualizado(Paciente paciente) {
        Optional<Paciente> pacienteActualizadoOptional = pacienteRepository.findById(paciente.getCodigo());
        assertTrue(pacienteActualizadoOptional.isPresent());
        Paciente pacienteActualizado = pacienteActualizadoOptional.get();
        assertEquals("EPS Modificada", pacienteActualizado.getEps());
    }

    private void eliminarPaciente(Paciente paciente) {
        pacienteRepository.deleteById(paciente.getCodigo());
    }

    private void verificarPacienteEliminado(Paciente paciente) {
        Optional<Paciente> pacienteEliminadoOptional = pacienteRepository.findById(paciente.getCodigo());
        assertFalse(pacienteEliminadoOptional.isPresent());
    }

    private void eliminarUsuario(Usuario usuario) {
        usuarioRepository.deleteById(usuario.getCedula());
    }

    private void eliminarPacienteYUsuario(Paciente paciente, Usuario usuario) {
        eliminarPaciente(paciente);
        eliminarUsuario(usuario);
    }
}
