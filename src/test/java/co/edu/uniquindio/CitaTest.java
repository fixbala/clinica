package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Cita;
import co.edu.uniquindio.modelo.Horario;
import co.edu.uniquindio.modelo.Medico;
import co.edu.uniquindio.modelo.Paciente;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.CitaRepository;
import co.edu.uniquindio.repositorios.MedicoRepository;
import co.edu.uniquindio.repositorios.PacienteRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CitaTest {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarCita() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setUsuario(usuarioMedico);
        medicoRepository.save(medico);

        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("654321");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una cita para la prueba
        Cita nuevaCita = new Cita("C001", new Date(), new Date(), LocalTime.of(14, 0), "Pendiente", paciente, medico, null, "Consulta de rutina");
        Cita guardado = citaRepository.save(nuevaCita);

        Assertions.assertEquals(nuevaCita, guardado, "La cita guardada no coincide con la cita creada");
        Assertions.assertNotNull(guardado);
    }

    @Test
    public void testActualizarCita() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setUsuario(usuarioMedico);
        medicoRepository.save(medico);

        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("654321");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una cita para la prueba
        Cita nuevaCita = new Cita("C001", new Date(), new Date(), LocalTime.of(14, 0), "Pendiente", paciente, medico, null, "Consulta de rutina");
        Cita guardado = citaRepository.save(nuevaCita);

        // Modificar la cita
        guardado.setMotivo_consulta("Consulta de seguimiento");
        Cita actualizado = citaRepository.save(guardado);

        // Recuperar la cita actualizada de la base de datos
        Optional<Cita> citaRecuperada = citaRepository.findById(actualizado.getId_cita());

        // Verificar que la cita fue actualizada correctamente
        Assertions.assertTrue(citaRecuperada.isPresent(), "La cita no se encontró en la base de datos");
        Assertions.assertEquals("Consulta de seguimiento", citaRecuperada.get().getMotivo_consulta(), "El motivo de la consulta no se actualizó correctamente");

    }

    @Test
    public void testObtenerCitaPorId() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setUsuario(usuarioMedico);
        medicoRepository.save(medico);

        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("654321");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una cita para la prueba
        Cita nuevaCita = new Cita("C001", new Date(), new Date(), LocalTime.of(14, 0), "Pendiente", paciente, medico, null, "Consulta de rutina");
        Cita guardado = citaRepository.save(nuevaCita);

        // Obtener la cita por ID
        Cita obtenida = citaRepository.findById(guardado.getId_cita()).orElse(null);

        // Verificar que la cita se haya obtenido correctamente
        assertNotNull(obtenida);
        assertEquals(guardado, obtenida, "La cita obtenida no coincide con la cita guardada");
    }

    @Test
    public void testObtenerTodasLasCitas() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setUsuario(usuarioMedico);
        medicoRepository.save(medico);

        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("654321");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear varias citas para la prueba
        Cita cita1 = new Cita("C001", new Date(), new Date(), LocalTime.of(14, 0), "Pendiente", paciente, medico, null, "Consulta de rutina");
        Cita cita2 = new Cita("C002", new Date(), new Date(), LocalTime.of(15, 0), "Pendiente", paciente, medico, null, "Examen de vista");
        Cita cita3 = new Cita("C003", new Date(), new Date(), LocalTime.of(16, 0), "Pendiente", paciente, medico, null, "Control de presión");
        citaRepository.save(cita1);
        citaRepository.save(cita2);
        citaRepository.save(cita3);


        // Obtener todas las citas
        List<Cita> todasLasCitas = citaRepository.findAll();

        // Verificar que se hayan obtenido las citas correctamente
        assertNotNull(todasLasCitas);
        assertEquals(3, todasLasCitas.size(), "La cantidad de citas obtenidas no es la esperada");
    }

    @Test
    public void testEliminarCita() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setUsuario(usuarioMedico);
        medicoRepository.save(medico);

        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("654321");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una cita para la prueba
        Cita nuevaCita = new Cita("C001", new Date(), new Date(), LocalTime.of(14, 0), "Pendiente", paciente, medico, null, "Consulta de rutina");
        Cita guardado = citaRepository.save(nuevaCita);

        // Eliminar la cita
        citaRepository.deleteById(guardado.getId_cita());

        // Intentar obtener la cita eliminada
        Optional<Cita> citaEliminada = citaRepository.findById(guardado.getId_cita());

        // Verificar que la cita no existe después de eliminarla
        assertEquals(Optional.empty(), citaEliminada, "La cita no fue eliminada correctamente");
    }

    @Test
    public void testHorarioPermiteAgendarCita() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba con un horario específico (por ejemplo, de 13:00 a 15:00 los lunes)
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setUsuario(usuarioMedico);

        // Agregar un horario al médico
        Horario horario = new Horario(medico, DayOfWeek.MONDAY, LocalTime.of(13, 0), LocalTime.of(15, 0));
        medico.getHorarios().add(horario);

        medicoRepository.save(medico);

        // Crear un usuario para el paciente
        Usuario usuarioPaciente = new Usuario();
        usuarioPaciente.setCedula("654321");
        usuarioPaciente.setNombre("Nombre de Paciente");
        usuarioRepository.save(usuarioPaciente);

        // Crear un paciente para la prueba
        Paciente paciente = new Paciente();
        paciente.setCodigo("P001");
        paciente.setEps("EPS Test");
        paciente.setUsuario(usuarioPaciente);
        pacienteRepository.save(paciente);

        // Crear una cita para la prueba (dentro del rango del horario del médico)
        Cita nuevaCita = new Cita("C001", new Date(), new Date(), LocalTime.of(14, 0), "Pendiente", paciente, medico, null, "Consulta de rutina");
        Cita guardado = citaRepository.save(nuevaCita);

        Assertions.assertEquals(nuevaCita, guardado, "La cita guardada no coincide con la cita creada");
        Assertions.assertNotNull(guardado);
    }
    
    

}