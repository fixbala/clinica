package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Medico;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.MedicoRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        usuario.setPassword("password");  // Configurar la contraseña sin codificar
    
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
        medico.setUsuario(usuario);
    
        // Guardar el médico
        medicoRepository.save(medico);
    }
    


    @Test
    public void iniciarSesionMedicoExistenteTest() {
        // Intentar iniciar sesión con el médico y contraseña correctos
        Optional<Medico> medicoEncontrado = medicoRepository.findByCodigoAndUsuario_Password("M001", "password");

        // Comprobamos que el médico haya sido encontrado
        assertTrue(medicoEncontrado.isPresent());
    }

    @Test
    public void iniciarSesionMedicoNoExistenteTest() {
        // Intentar iniciar sesión con un médico que no existe
        Optional<Medico> medicoEncontrado = medicoRepository.findByCodigoAndUsuario_Password("M999", "password");

        // Comprobamos que el médico no haya sido encontrado
        assertTrue(medicoEncontrado.isEmpty());
    }

    @Test
    public void iniciarSesionContrasenaIncorrectaTest() {
        // Intentar iniciar sesión con una contraseña incorrecta
        Optional<Medico> medicoEncontrado = medicoRepository.findByCodigoAndUsuario_Password("M001", "contrasenaincorrecta");

        // Comprobamos que el médico no haya sido encontrado
        assertTrue(medicoEncontrado.isEmpty());
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
        medico.setUsuario(usuario);

        // Guardar el médico
        medicoRepository.save(medico);

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
        medicoRepository.save(medico);

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

    @Test
    @Transactional
    public void testFiltrarMedicosPorEspecialidad() {
        // Filtrar médicos por especialidad
        List<Medico> medicosPorEspecialidad = medicoRepository.findByEspecialidad("Cardiología");

        // Verificar que la lista no esté vacía
        assertFalse(medicosPorEspecialidad.isEmpty());

        // Verificar que todos los médicos en la lista tengan la especialidad esperada
        for (Medico medico : medicosPorEspecialidad) {
            assertEquals("Cardiología", medico.getEspecialidad());
        }
    }

}
