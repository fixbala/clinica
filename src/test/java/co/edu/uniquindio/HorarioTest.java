package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Horario;
import co.edu.uniquindio.modelo.Medico;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.HorarioRepository;
import co.edu.uniquindio.repositorios.MedicoRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HorarioTest {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarHorario() {
        // Crear un usuario para el médico
        Usuario usuario = new Usuario();
        usuario.setCedula("123456");
        usuario.setNombre("Nombre de Medico");
        usuarioRepository.save(usuario);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setHora_inicio(LocalTime.of(9, 0));
        medico.setHora_final(LocalTime.of(17, 0));
        medico.setUsuario(usuario);
        medicoRepository.save(medico);

        Horario nuevoHorario = new Horario(medico, DayOfWeek.TUESDAY, LocalTime.of(14, 0), LocalTime.of(17, 0));
        horarioRepository.save(nuevoHorario);

        // Verificar que el horario se haya guardado correctamente
        Optional<Horario> horarioGuardado = horarioRepository.findById(nuevoHorario.getId());

        // Verificar que el horario guardado no es nulo
        assertTrue(horarioGuardado.isPresent(), "El horario no se ha guardado correctamente");

        // Comparar el horario guardado con el horario creado
        assertEquals(nuevoHorario, horarioGuardado.get(), "El horario guardado no coincide con el horario creado");
    }

    @Test
    public void testObtenerHorarioPorId() {
        // Crear un horario para la prueba
        Horario horario = new Horario();
        horarioRepository.save(horario);

        // Obtener el horario por ID
        Optional<Horario> horarioObtenido = horarioRepository.findById(horario.getId());

        // Verificar que el horario obtenido no es nulo
        assertTrue(horarioObtenido.isPresent(), "El horario no se ha obtenido correctamente por ID");

        // Comparar el horario obtenido con el horario creado
        assertEquals(horario.getId(), horarioObtenido.get().getId(), "El ID del horario obtenido no coincide con el horario creado");
    }

    @Test
    public void testEliminarHorario() {
        // Crear un horario para la prueba
        Horario horario = new Horario();
        horarioRepository.save(horario);

        // Obtener el ID del horario
        Long idHorario = horario.getId();

        // Eliminar el horario por ID
        horarioRepository.deleteById(idHorario);

        // Verificar que el horario ha sido eliminado correctamente
        Optional<Horario> horarioEliminado = horarioRepository.findById(idHorario);
        assertTrue(horarioEliminado.isEmpty(), "El horario no se ha eliminado correctamente");
    }

}
