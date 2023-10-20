package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Paciente;
import co.edu.uniquindio.modelo.Pqrs;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.PacienteRepository;
import co.edu.uniquindio.repositorios.PqrsRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PqrsTest {

    @Autowired
    private PqrsRepository pqrsRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarPqrs() {
        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("123456");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una PQRS para la prueba
        Pqrs nuevaPqrs = new Pqrs("R001", new Date(), 1, paciente, "Solicitud de información", null);
        Pqrs pqrsGuardada = pqrsRepository.save(nuevaPqrs);

        // Verificar que la PQRS fue guardada correctamente
        Assertions.assertEquals(nuevaPqrs, pqrsGuardada, "La PQRS guardada no coincide con la PQRS creada");
        Assertions.assertNotNull(pqrsGuardada);
    }

    @Test
    public void testEditarPqrs() {
        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("123456");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una PQRS para la prueba
        Pqrs nuevaPqrs = new Pqrs("R001", new Date(), 1, paciente, "Solicitud de información", null);
        Pqrs pqrsGuardada = pqrsRepository.save(nuevaPqrs);

        // Modificar la PQRS
        pqrsGuardada.setDetalle("Nueva solicitud de información");
        Pqrs pqrsActualizada = pqrsRepository.save(pqrsGuardada);

        // Recuperar la PQRS actualizada de la base de datos
        Pqrs pqrsRecuperada = pqrsRepository.findById(pqrsActualizada.getNum_radicado()).orElse(null);

        // Verificar que la PQRS fue actualizada correctamente
        Assertions.assertNotNull(pqrsRecuperada);
        Assertions.assertEquals("Nueva solicitud de información", pqrsRecuperada.getDetalle(), "El detalle de la PQRS no se actualizó correctamente");
    }

    @Test
    public void testEliminarPqrs() {
        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("123456");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una PQRS para la prueba
        Pqrs nuevaPqrs = new Pqrs("R001", new Date(), 1, paciente, "Solicitud de información", null);
        Pqrs pqrsGuardada = pqrsRepository.save(nuevaPqrs);

        // Eliminar la PQRS
        pqrsRepository.deleteById(pqrsGuardada.getNum_radicado());

        // Intentar obtener la PQRS eliminada
        Pqrs pqrsEliminada = pqrsRepository.findById(pqrsGuardada.getNum_radicado()).orElse(null);

        // Verificar que la PQRS no existe después de eliminarla
        Assertions.assertNull(pqrsEliminada, "La PQRS no fue eliminada correctamente");
    }
}
