package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Atencion;
import co.edu.uniquindio.modelo.Cita;
import co.edu.uniquindio.modelo.Medico;
import co.edu.uniquindio.modelo.Paciente;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.AtencionRepository;
import co.edu.uniquindio.repositorios.CitaRepository;
import co.edu.uniquindio.repositorios.MedicoRepository;
import co.edu.uniquindio.repositorios.PacienteRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AtencionTest {

    @Autowired
    private AtencionRepository atencionRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarAtencion() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setHora_inicio(LocalTime.of(9, 0));
        medico.setHora_final(LocalTime.of(17, 0));
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
        Cita citaGuardada = citaRepository.save(nuevaCita);

        // Crear una atención para la prueba
        Atencion nuevaAtencion = new Atencion("A001", null, citaGuardada, "Dolor de cabeza", "Ninguno", "Tomar medicamento");
        Atencion atencionGuardada = atencionRepository.save(nuevaAtencion);

        // Verificar que la atención fue guardada correctamente
        Assertions.assertEquals(nuevaAtencion, atencionGuardada, "La atención guardada no coincide con la atención creada");
        Assertions.assertNotNull(atencionGuardada);
    }

    @Test
    public void testActualizarAtencion() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setHora_inicio(LocalTime.of(9, 0));
        medico.setHora_final(LocalTime.of(17, 0));
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
        Cita citaGuardada = citaRepository.save(nuevaCita);

        // Crear una atención para la prueba
        Atencion nuevaAtencion = new Atencion("A001", null, citaGuardada, "Dolor de cabeza", "Ninguno", "Tomar medicamento");
        Atencion atencionGuardada = atencionRepository.save(nuevaAtencion);

        // Modificar la atención
        atencionGuardada.setSintomas("Dolor persistente en la cabeza");
        Atencion atencionActualizada = atencionRepository.save(atencionGuardada);

        // Recuperar la atención actualizada de la base de datos
        Optional<Atencion> atencionRecuperada = atencionRepository.findById(atencionActualizada.getId_atencion());

        // Verificar que la atención fue actualizada correctamente
        Assertions.assertTrue(atencionRecuperada.isPresent(), "La atención no se encontró en la base de datos");
        Assertions.assertEquals("Dolor persistente en la cabeza", atencionRecuperada.get().getSintomas(), "Los síntomas de la atención no se actualizaron correctamente");
    }

    @Test
    public void testObtenerAtencionPorId() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setHora_inicio(LocalTime.of(9, 0));
        medico.setHora_final(LocalTime.of(17, 0));
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
        Cita citaGuardada = citaRepository.save(nuevaCita);

        // Crear una atención para la prueba
        Atencion nuevaAtencion = new Atencion("A001", null, citaGuardada, "Dolor de cabeza", "Ninguno", "Tomar medicamento");
        Atencion atencionGuardada = atencionRepository.save(nuevaAtencion);

        // Obtener la atención por ID
        Atencion atencionObtenida = atencionRepository.findById(atencionGuardada.getId_atencion()).orElse(null);

        // Verificar que la atención se haya obtenido correctamente
        Assertions.assertNotNull(atencionObtenida);
        Assertions.assertEquals(atencionGuardada, atencionObtenida, "La atención obtenida no coincide con la atención guardada");
    }

    @Test
    public void testObtenerTodasLasAtenciones() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setHora_inicio(LocalTime.of(9, 0));
        medico.setHora_final(LocalTime.of(17, 0));
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

        // Crear varias atenciones para la prueba
        Atencion atencion1 = new Atencion("A001", null, cita1, "Dolor de cabeza", "Ninguno", "Tomar medicamento");
        Atencion atencion2 = new Atencion("A002", null, cita2, "Visión borrosa", "Ninguno", "Recetar lentes");
        Atencion atencion3 = new Atencion("A003", null, cita3, "Presión alta", "Ninguno", "Recomendar dieta");
        atencionRepository.save(atencion1);
        atencionRepository.save(atencion2);
        atencionRepository.save(atencion3);

        // Obtener todas las atenciones
        List<Atencion> todasLasAtenciones = atencionRepository.findAll();

        // Verificar que se hayan obtenido las atenciones correctamente
        Assertions.assertNotNull(todasLasAtenciones);
        Assertions.assertEquals(3, todasLasAtenciones.size(), "La cantidad de atenciones obtenidas no es la esperada");
    }

    @Test
    public void testEliminarAtencion() {
        // Crear un usuario para el médico
        Usuario usuarioMedico = new Usuario();
        usuarioMedico.setCedula("123456");
        usuarioMedico.setNombre("Nombre de Medico");
        usuarioRepository.save(usuarioMedico);

        // Crear un médico para la prueba
        Medico medico = new Medico();
        medico.setCodigo("M001");
        medico.setEspecialidad("Oftalmología");
        medico.setHora_inicio(LocalTime.of(9, 0));
        medico.setHora_final(LocalTime.of(17, 0));
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
        Cita citaGuardada = citaRepository.save(nuevaCita);

        // Crear una atención para la prueba
        Atencion nuevaAtencion = new Atencion("A001", null, citaGuardada, "Dolor de cabeza", "Ninguno", "Tomar medicamento");
        Atencion atencionGuardada = atencionRepository.save(nuevaAtencion);

        // Eliminar la atención
        atencionRepository.deleteById(atencionGuardada.getId_atencion());

        // Intentar obtener la atención eliminada
        Optional<Atencion> atencionEliminada = atencionRepository.findById(atencionGuardada.getId_atencion());

        // Verificar que la atención no existe después de eliminarla
        Assertions.assertEquals(Optional.empty(), atencionEliminada, "La atención no fue eliminada correctamente");
    }

}