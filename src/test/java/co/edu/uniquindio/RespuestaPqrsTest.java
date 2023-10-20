package co.edu.uniquindio;

import co.edu.uniquindio.modelo.Pqrs;
import co.edu.uniquindio.modelo.RespuestaPqrs;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.PqrsRepository;
import co.edu.uniquindio.repositorios.RespuestaPqrsRepository;
import co.edu.uniquindio.repositorios.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RespuestaPqrsTest {

    @Autowired
    private RespuestaPqrsRepository respuestaPqrsRepository;

    @Autowired
    private PqrsRepository pqrsRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testGuardarRespuestaPqrs() {
        // Crear un usuario para el paciente
        Usuario usuario = new Usuario();
        usuario.setCedula("123456");
        usuario.setNombre("Nombre de Usuario");
        usuarioRepository.save(usuario);

        Pqrs pqrs = new Pqrs("R001", new Date(), 1, null, "Solicitud de información", null);
        pqrsRepository.save(pqrs);

        // Crear una RespuestaPqrs para la prueba
        RespuestaPqrs respuestaPqrs = new RespuestaPqrs("RP001", new Date(), "Respuesta a la PQRS", pqrs);
        RespuestaPqrs respuestaGuardada = respuestaPqrsRepository.save(respuestaPqrs);

        // Verificar que la RespuestaPqrs fue guardada correctamente
        Assertions.assertEquals(respuestaPqrs, respuestaGuardada, "La RespuestaPqrs guardada no coincide con la RespuestaPqrs creada");
        Assertions.assertNotNull(respuestaGuardada);
    }

    @Test
    public void testEditarRespuestaPqrs() {
    // Crear un usuario para el paciente
    Usuario usuario = new Usuario();
    usuario.setCedula("123456");
    usuario.setNombre("Nombre de Usuario");
    usuarioRepository.save(usuario);

    Pqrs pqrs = new Pqrs("R001", new Date(), 1, null, "Solicitud de información", null);
    pqrsRepository.save(pqrs);

    // Crear una RespuestaPqrs para la prueba
    RespuestaPqrs respuestaPqrs = new RespuestaPqrs("RP001", new Date(), "Respuesta inicial", pqrs);
    respuestaPqrsRepository.save(respuestaPqrs);

    // Modificar la respuesta
    respuestaPqrs.setTexto("Respuesta modificada");
    RespuestaPqrs respuestaEditada = respuestaPqrsRepository.save(respuestaPqrs);

    // Recuperar la respuesta actualizada de la base de datos
    RespuestaPqrs respuestaRecuperada = respuestaPqrsRepository.findById(respuestaEditada.getId_respuesta()).orElse(null);

    // Verificar que la respuesta fue editada correctamente
    Assertions.assertTrue(respuestaRecuperada != null && respuestaEditada != null);
    Assertions.assertEquals("Respuesta modificada", respuestaRecuperada.getTexto(), "El texto de la respuesta no se actualizó correctamente");
}

    @Test
    public void testEliminarRespuestaPqrs() {
    // Crear un usuario para el paciente
    Usuario usuario = new Usuario();
    usuario.setCedula("123456");
    usuario.setNombre("Nombre de Usuario");
    usuarioRepository.save(usuario);

    Pqrs pqrs = new Pqrs("R001", new Date(), 1, null, "Solicitud de información", null);
    pqrsRepository.save(pqrs);

    // Crear una RespuestaPqrs para la prueba
    RespuestaPqrs respuestaPqrs = new RespuestaPqrs("RP001", new Date(), "Respuesta inicial", pqrs);
    respuestaPqrsRepository.save(respuestaPqrs);

    // Eliminar la respuesta
    respuestaPqrsRepository.deleteById(respuestaPqrs.getId_respuesta());

    // Intentar obtener la respuesta eliminada
    RespuestaPqrs respuestaEliminada = respuestaPqrsRepository.findById(respuestaPqrs.getId_respuesta()).orElse(null);

    // Verificar que la respuesta no existe después de eliminarla
    Assertions.assertNull(respuestaEliminada, "La respuesta no fue eliminada correctamente");
}


}
