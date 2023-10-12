// package co.edu.uniquindio.test;

// import co.edu.uniquindio.modelo.Medico;
// import co.edu.uniquindio.modelo.Usuario;
// import co.edu.uniquindio.repositorios.MedicoRepository;
// import co.edu.uniquindio.servicios.MedicoService;
// import co.edu.uniquindio.servicios.MedicoServiceImpl;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.boot.test.mock.mockito.MockBean;

// import java.time.LocalTime;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @DataJpaTest
// public class MedicoRepoTest {

//     @MockBean
//     private MedicoRepository medicoRepository;

//     @Autowired
//     private MedicoService medicoService;

//     private Medico medico;

//     @BeforeEach
//     public void setUp() {
//         Usuario usuario = new Usuario();
//         usuario.setCedula("123456");
//         usuario.setNombre("Nombre de Medico");
//         medico = new Medico("M001", "Cardiología", LocalTime.of(8, 0), LocalTime.of(18, 0), usuario);
//     }

//     @Test
//     public void testGuardarMedico() {
//         when(medicoRepository.save(medico)).thenReturn(medico);

//         Medico medicoGuardado = medicoService.guardarMedico(medico);

//         assertNotNull(medicoGuardado);
//         assertEquals("Cardiología", medicoGuardado.getEspecialidad());

//         verify(medicoRepository, times(1)).save(medico);
//     }

//     @Test
//     public void testObtenerMedicoPorCodigo() {
//         when(medicoRepository.findById("M001")).thenReturn(Optional.of(medico));

//         Optional<Medico> medicoObtenido = medicoService.obtenerMedicoPorCodigo("M001");

//         assertTrue(medicoObtenido.isPresent());
//         assertEquals("Nombre de Medico", medicoObtenido.get().getUsuario().getNombre());

//         verify(medicoRepository, times(1)).findById("M001");
//     }

//     @Test
//     public void testObtenerTodosLosMedicos() {
//         List<Medico> medicos = new ArrayList<>();
//         medicos.add(medico);

//         when(medicoRepository.findAll()).thenReturn(medicos);

//         List<Medico> medicosObtenidos = medicoService.obtenerTodosLosMedicos();

//         assertFalse(medicosObtenidos.isEmpty());
//         assertEquals("Cardiología", medicosObtenidos.get(0).getEspecialidad());

//         verify(medicoRepository, times(1)).findAll();
//     }

//     @Test
//     public void testEliminarMedico() {
//         doNothing().when(medicoRepository).deleteById("M001");

//         medicoService.eliminarMedico("M001");

//         verify(medicoRepository, times(1)).deleteById("M001");
//     }
// }
