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
import java.util.ArrayList;
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

    @Test
    public void testGestionHorariosMedico() {
        // Crear un médico para la prueba sin horarios inicialmente
        Usuario usuario = new Usuario();
        usuario.setCedula("123456");
        usuario.setNombre("Nombre de Medico");
        usuarioRepository.save(usuario);

        Medico medico = new Medico("M001", "Especialidad", usuario, new ArrayList<>());

        // Crear horarios para agregar al médico
        Horario horario1 = new Horario(medico, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(12, 0));
        Horario horario2 = new Horario(medico, DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(17, 0));

        // Agregar horarios al médico
        medico.getHorarios().add(horario1);
        medico.getHorarios().add(horario2);

        medicoRepository.save(medico);

        // Verificar que los horarios se hayan guardado correctamente
        Medico medicoConHorarios = medicoRepository.findById(medico.getCodigo()).orElse(null);
        assertNotNull(medicoConHorarios);
        assertEquals(2, medicoConHorarios.getHorarios().size(), "La cantidad de horarios no es la esperada");

        // Actualizar un horario
        Horario horarioActualizado = medicoConHorarios.getHorarios().get(0);
        horarioActualizado.setHora_inicio(LocalTime.of(10, 0));
        horarioActualizado.setHora_final(LocalTime.of(13, 0));
        medicoRepository.save(medicoConHorarios);

        // Verificar que el horario se haya actualizado correctamente
        Medico medicoConHorariosActualizados = medicoRepository.findById(medico.getCodigo()).orElse(null);
        assertNotNull(medicoConHorariosActualizados);
        assertEquals(LocalTime.of(10, 0), medicoConHorariosActualizados.getHorarios().get(0).getHora_inicio(), "La hora de inicio del horario no se actualizó correctamente");

        // Eliminar un horario
        Horario horarioAEliminar = medicoConHorariosActualizados.getHorarios().get(0);
        medicoConHorariosActualizados.getHorarios().remove(horarioAEliminar);
        medicoRepository.save(medicoConHorariosActualizados);

        // Verificar que el horario se haya eliminado correctamente
        Medico medicoSinHorarioEliminado = medicoRepository.findById(medico.getCodigo()).orElse(null);
        assertNotNull(medicoSinHorarioEliminado);
        assertEquals(1, medicoSinHorarioEliminado.getHorarios().size(), "La cantidad de horarios no es la esperada después de eliminar uno");
    }


    @Test   
    public void testAgregarHorarioNoValido() {
    // Crear un médico sin horarios inicialmente
    Usuario usuario = new Usuario();
    usuario.setCedula("123456");
    usuario.setNombre("Nombre de Medico");
    usuarioRepository.save(usuario);

    Medico medico = new Medico("M001", "Especialidad", usuario, new ArrayList<>());

    // Crear horarios para agregar al médico
    Horario horario1 = new Horario(medico, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(12, 0));
    Horario horario2 = new Horario(medico, DayOfWeek.WEDNESDAY, LocalTime.of(14, 0), LocalTime.of(17, 0));

    // Agregar horarios al médico
    medico.getHorarios().add(horario1);
    medico.getHorarios().add(horario2);

    medicoRepository.save(medico);

    // Crear un horario no válido para agregar al médico (misma hora y día que horario1)
    Horario horarioNoValido = new Horario(medico, DayOfWeek.MONDAY, LocalTime.of(9, 0), LocalTime.of(12, 0));

    // Intentar agregar el horario no válido al médico
    medico.getHorarios().add(horarioNoValido);
    medicoRepository.save(medico);

    // Verificar que el horario no válido no se haya agregado
    Medico medicoSinHorarioNoValido = medicoRepository.findById(medico.getCodigo()).orElse(null);
    assertNotNull(medicoSinHorarioNoValido);
}

        
}
