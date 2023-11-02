package co.edu.uniquindio.controladores;
import co.edu.uniquindio.modelo.Usuario;
import co.edu.uniquindio.repositorios.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AutenticacionController {

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyecta el repositorio de usuarios

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Credenciales credenciales) {
        // Buscar el usuario por cédula y contraseña en la base de datos
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCedulaAndPassword(credenciales.getCedula(), credenciales.getPassword());
    
        if (usuarioOptional.isPresent()) {
            // El inicio de sesión es exitoso, obtén el usuario del Optional
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inicio de sesión fallido");
        }
    }
    

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario usuarioNuevo) {
        // Verificar si el usuario ya existe en la base de datos
        Usuario usuarioExistente = usuarioRepository.findByCedula(usuarioNuevo.getCedula());

        if (usuarioExistente == null) {
            // Crear un nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setCedula(usuarioNuevo.getCedula());
            nuevoUsuario.setPassword(usuarioNuevo.getPassword());
            nuevoUsuario.setNombre(usuarioNuevo.getNombre());
            nuevoUsuario.setEmail(usuarioNuevo.getEmail());
            nuevoUsuario.setTelefono(usuarioNuevo.getTelefono());
            nuevoUsuario.setCiudad(usuarioNuevo.getCiudad());

            usuarioRepository.save(nuevoUsuario);

            // Puedes devolver una respuesta de éxito
            return ResponseEntity.ok("Registro exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe");
        }
    }


    @GetMapping("/hola")
    public ResponseEntity<Map<String, String>> holaMundo() {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Hola Mundo");

        return ResponseEntity.ok(response);
    }
    
}
