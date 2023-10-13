package co.edu.uniquindio.test;

import co.edu.uniquindio.modelo.Medico;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.MedicoRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicoTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Medico medico;

    @BeforeEach
    public void setUp() {
        // Crear un usuario para el médico
        Usuario usuario = new Usuario();
        usuario.setCedula("123456");
        usuario.setNombre("Nombre de Medico");

        // Verificar si el usuario ya existe en la base de datos
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getCedula());

        if (usuarioExistente.isPresent()) {
            // El usuario ya existe, lo usamos
            usuario = usuarioExistente.get();
        } else {
            // El usuario no existe, lo guardamos
            usuario = usuarioRepository.save(usuario);
        }

        // Crear un médico para la prueba
        medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Cardiología");
        medico.setHora_inicio(LocalTime.of(8, 0));
        medico.setHora_final(LocalTime.of(18, 0));
        medico.setUsuario(usuario);

        // Guardar el médico
        medicoRepository.save(medico);
    }

    @Test
    public void testGuardarMedico() {
        // Crear un usuario para el médico
        Usuario usuario = new Usuario();
        usuario.setCedula("123456");
        usuario.setNombre("Nombre de Medico");

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M002");
        medico.setEspecialidad("Oftalmología");
        medico.setHora_inicio(LocalTime.of(9, 0));
        medico.setHora_final(LocalTime.of(17, 0));
        medico.setUsuario(usuario);

        // Guardar el médico
        Medico medicoGuardado = medicoRepository.save(medico);

        // Verificar que el médico se haya guardado correctamente
        Optional<Medico> medicoRecuperado = medicoRepository.findById(medico.getCodigo());
        assertTrue(medicoRecuperado.isPresent());
        assertEquals("Oftalmología", medicoRecuperado.get().getEspecialidad());
    }

    @Test
    public void testActualizarMedico() {
        // Modificar la especialidad del médico
        medico.setEspecialidad("Neurología");

        // Actualizar el médico
        Medico medicoActualizado = medicoRepository.save(medico);

        // Verificar que el médico se haya actualizado correctamente
        Optional<Medico> medicoRecuperado = medicoRepository.findById(medico.getCodigo());
        assertTrue(medicoRecuperado.isPresent());
        assertEquals("Neurología", medicoRecuperado.get().getEspecialidad());
    }

    @Test
    public void testEliminarMedico() {
        // Eliminar el médico
        medicoRepository.deleteById(medico.getCodigo());

        // Verificar que el médico se haya eliminado correctamente
        Optional<Medico> medicoEliminado = medicoRepository.findById(medico.getCodigo());
        assertFalse(medicoEliminado.isPresent());
    }
}
