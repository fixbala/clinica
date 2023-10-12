// package co.edu.uniquindio.test;

// import co.edu.uniquindio.modelo.Paciente;
// import co.edu.uniquindio.modelo.Usuario;
// import co.edu.uniquindio.repositorios.PacienteRepository;
// import co.edu.uniquindio.repositorios.UsuarioRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import java.util.Date;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest
// public class PacienteRepoTest {

//     @Autowired
//     private PacienteRepository pacienteRepository;

//     @Autowired
//     private UsuarioRepository usuarioRepository;

//     @Test
//     public void testGuardarPaciente() {
//         Usuario usuario = crearUsuario("123456", "Nombre de Usuario");

//         Paciente paciente = crearPaciente("P001", new Date(), "EPS Test", "O+", usuario);

//         guardarPaciente(paciente);

//         verificarPacienteGuardado(paciente);

//         eliminarPacienteYUsuario(paciente, usuario);
//     }

//     @Test
//     public void testActualizarPaciente() {
//         Usuario usuario = crearUsuario("123456", "Nombre de Usuario");

//         Paciente paciente = crearPaciente("P001", new Date(), "EPS Test", "O+", usuario);

//         guardarPaciente(paciente);

//         verificarPacienteGuardado(paciente);

//         paciente.setEps("EPS Modificada");
//         actualizarPaciente(paciente);

//         verificarPacienteActualizado(paciente);

//         eliminarPacienteYUsuario(paciente, usuario);
//     }

//     @Test
//     public void testEliminarPaciente() {
//         Usuario usuario = crearUsuario("123456", "Nombre de Usuario");

//         Paciente paciente = crearPaciente("P001", new Date(), "EPS Test", "O+", usuario);

//         guardarPaciente(paciente);

//         verificarPacienteGuardado(paciente);

//         eliminarPaciente(paciente);

//         verificarPacienteEliminado(paciente);

//         eliminarUsuario(usuario);
//     }

//     // Métodos auxiliares

//     private Usuario crearUsuario(String cedula, String nombre) {
//         Usuario usuario = new Usuario();
//         usuario.setCedula(cedula);
//         usuario.setNombre(nombre);
//         // Otros atributos según tus necesidades
//         usuarioRepository.save(usuario);
//         return usuario;
//     }

//     private Paciente crearPaciente(String codigo, Date fecha, String eps, String tipoSangre, Usuario usuario) {
//         Paciente paciente = new Paciente();
//         paciente.setCodigo(codigo);
//         paciente.setFecha(fecha);
//         paciente.setEps(eps);
//         paciente.setTipo_sangre(tipoSangre);
//         paciente.setUsuario(usuario);
//         return paciente;
//     }

//     private void guardarPaciente(Paciente paciente) {
//         pacienteRepository.save(paciente);
//     }

//     private void verificarPacienteGuardado(Paciente paciente) {
//         Optional<Paciente> pacienteGuardadoOptional = pacienteRepository.findById(paciente.getCodigo());
//         assertTrue(pacienteGuardadoOptional.isPresent());
//         Paciente pacienteGuardado = pacienteGuardadoOptional.get();
//         assertEquals(paciente.getEps(), pacienteGuardado.getEps());
//     }

//     private void actualizarPaciente(Paciente paciente) {
//         pacienteRepository.save(paciente);
//     }

//     private void verificarPacienteActualizado(Paciente paciente) {
//         Optional<Paciente> pacienteActualizadoOptional = pacienteRepository.findById(paciente.getCodigo());
//         assertTrue(pacienteActualizadoOptional.isPresent());
//         Paciente pacienteActualizado = pacienteActualizadoOptional.get();
//         assertEquals("EPS Modificada", pacienteActualizado.getEps());
//     }

//     private void eliminarPaciente(Paciente paciente) {
//         pacienteRepository.deleteById(paciente.getCodigo());
//     }

//     private void verificarPacienteEliminado(Paciente paciente) {
//         Optional<Paciente> pacienteEliminadoOptional = pacienteRepository.findById(paciente.getCodigo());
//         assertFalse(pacienteEliminadoOptional.isPresent());
//     }

//     private void eliminarUsuario(Usuario usuario) {
//         usuarioRepository.deleteById(usuario.getCedula());
//     }

//     private void eliminarPacienteYUsuario(Paciente paciente, Usuario usuario) {
//         eliminarPaciente(paciente);
//         eliminarUsuario(usuario);
//     }
// }
